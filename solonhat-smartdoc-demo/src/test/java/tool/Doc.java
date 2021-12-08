package tool;
import com.power.common.util.DateTimeUtil;
import com.power.doc.constants.DocGlobalConstants;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.SourceCodePath;
import org.junit.Test;
import org.noear.solonhat.smartdoc.SolonHtmlApiDocBuilder;

public class Doc {

    /**
     * 包括设置请求头，缺失注释的字段批量在文档生成期使用定义好的注释
     */
    @Test
    public void generate() {
        ApiConfig config = new ApiConfig();

        config.setServerUrl("http://localhost:8089");

        //设置用md5加密html文件名,不设置为true，html的文件名将直接为controller的名称
        config.setMd5EncryptedHtmlName(true);
        config.setStrict(false);//true会严格要求注释，推荐设置true
        config.setOutPath(DocGlobalConstants.HTML_DOC_OUT_PATH);//输出到static/doc下

        //不指定SourcePaths默认加载代码为项目src/main/java下的,如果项目的某一些实体来自外部代码可以一起加载
        config.setSourceCodePaths(
                SourceCodePath
                        .path()
                        .setDesc("demo")
                        .setPath("src/main/java")
        );

        long start = System.currentTimeMillis();
//        ApiDocBuilder.buildApiDoc(config);
        SolonHtmlApiDocBuilder.buildApiDoc(config);
//        HtmlApiDocBuilder.builderControllersApi(config);//此处使用HtmlApiDocBuilder，ApiDocBuilder提供markdown能力
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }
}
