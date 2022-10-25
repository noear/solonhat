package org.noear.solonhat.swagger2.integration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import io.swagger.models.*;
import io.swagger.models.parameters.*;
import org.noear.solon.Solon;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Controller;
import org.noear.solon.core.Aop;
import org.noear.solon.core.AopContext;
import org.noear.solon.core.Plugin;
import org.noear.solon.core.handle.*;
import org.noear.solon.core.route.Routing;
import org.noear.solon.core.util.PrintUtil;
import org.noear.solon.core.wrap.ParamWrap;
import org.noear.solonhat.swagger2.EnableSwagger2;
import org.noear.solonhat.swagger2.SwaggerController;

import java.util.LinkedHashMap;
import java.util.Map;

public class XPluginImp implements Plugin {
    Swagger swagger;

    @Override
    public void start(AopContext context) {
        if (Solon.app().source().getAnnotation(EnableSwagger2.class) == null) {
            return;
        }

        //创建 swagger bean
        swagger = Aop.getOrNew(Swagger.class);

        Aop.context().beanBuilderAdd(ApiModel.class, (clz, wrap, anno) -> {
            ModelImpl model = new ModelImpl();
            model.type(clz.getName());
            swagger.addDefinition(clz.getName(), model);
        });

        Aop.context().beanBuilderAdd(Api.class, (clz, wrap, anno) -> {
            Tag tag = new Tag();
            tag.name(clz.getName());

            swagger.addTag(tag);
        });


        context.beanMake(SwaggerController.class);

        context.beanOnloaded(this::onAppLoadEnd);

        PrintUtil.info("SwaggerApi", "url: http://localhost:" + Solon.cfg().serverPort() + "/v2/swagger.json");
    }

    private void onAppLoadEnd(AopContext context) {
        Info info = context.getBean(Info.class);

        if (info != null) {
            swagger.info(info);
        }

        swagger.host("localhost:" + Solon.global().port());
        swagger.basePath("/");
        swagger.scheme(Scheme.HTTP);

        buildTags();

        buildPaths();
    }

    private void buildTags() {
        Aop.context().beanForeach((bw) -> {
            if (bw.annotationGet(Controller.class) != null) {
                Tag tag = new Tag();
                tag.name(bw.clz().getName());

                swagger.addTag(tag);
            }
        });
    }

    private void buildPaths() {
        Map<String, Path> pathMap = new LinkedHashMap<>();

        for (Routing<Handler> route : Solon.global().router().getAll(Endpoint.main)) {
            if (route.target() instanceof Action) {
                Action action = (Action) route.target();

                Path path = new Path();
                {
                    switch (route.method()) {
                        case GET: {
                            path.get(buildPathPperation(route, true));
                            break;
                        }
                        case POST: {
                            path.post(buildPathPperation(route, false));
                            break;
                        }
                        case PUT: {
                            path.put(buildPathPperation(route, false));
                            break;
                        }
                        case DELETE: {
                            path.delete(buildPathPperation(route, false));
                            break;
                        }
                        case PATCH: {
                            path.patch(buildPathPperation(route, false));
                            break;
                        }
                        case HTTP: {
                            //path.get(buildPathPperation(route, true));
                            if (action.method().getParamWraps().length == 0) {
                                path.get(buildPathPperation(route, true));
                            } else {
                                path.post(buildPathPperation(route, false));
                            }
                            //path.put(buildPathPperation(route, false));
                            //path.delete(buildPathPperation(route, false));
                            //path.patch(buildPathPperation(route, false));
                            break;
                        }
                        default: {
                            path.post(buildPathPperation(route, false));
                        }
                    }
                }

                pathMap.put(route.path(), path);
            }
        }

        swagger.setPaths(pathMap);
    }

    private Operation buildPathPperation(Routing<Handler> route, boolean isGet) {
        Action action = (Action) route.target();


        Operation operation = new Operation();
        operation.addTag(action.controller().clz().getName());

        operation.summary(route.path());

        if (Utils.isNotEmpty(action.produces())) {
            operation.addProduces(action.produces());
        } else {
            operation.addProduces("*/*");
        }

        if (Utils.isNotEmpty(action.consumes())) {
            operation.addConsumes(action.consumes());
        }

        //添加请求参数
        for (ParamWrap p0 : action.method().getParamWraps()) {
            if (p0.getType() == Context.class) {
                continue;
            }

            ApiParam apiParam = p0.getParameter().getAnnotation(ApiParam.class);

            String n1 = "{" + p0.getName() + "}";
            SerializableParameter p1 = null;

            if (route.path().indexOf(n1) > 0) {
                p1 = new PathParameter();
            } else {
                if (isGet) {
                    p1 = new QueryParameter();
                } else {
                    p1 = new FormParameter();
                }
            }

            p1.setRequired(p1.getRequired());
            p1.setName(p0.getName());
            p1.setType(p0.getType().getSimpleName());

            if (apiParam != null) {
                p1.setRequired(apiParam.required());
                p1.setName(apiParam.name());
                p1.setAccess(apiParam.access());
            }

            operation.addParameter(p1);
        }

        return operation;
    }
}
