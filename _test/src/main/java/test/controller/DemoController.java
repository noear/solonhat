package test.controller;

import io.swagger.annotations.*;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;

/**
 * @author noear 2021/8/23 created
 *
 * 参考： https://www.cnblogs.com/cndarren/p/11769342.html
 */
@Api(value="示例",tags = "/")
@Controller
public class DemoController {

    @ApiOperation("hello")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")})
    @ApiResponses({@ApiResponse(code = 200, message = "id为空")})
    @Mapping("/")
    public String hello() {
        return "Hello";
    }
}
