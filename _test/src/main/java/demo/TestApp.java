package demo;

import org.noear.solon.Solon;
import org.noear.solonhat.swagger2.EnableSwagger2;

/**
 * @author noear 2021/3/6 created
 */
@EnableSwagger2
public class TestApp {
    public static void main(String[] args) {
        Solon.start(TestApp.class, args);
    }
}
