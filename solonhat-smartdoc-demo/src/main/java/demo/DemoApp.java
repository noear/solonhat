package demo;

import org.noear.solon.Solon;

public class DemoApp {
    public static void main(String[] args) {
        Solon.start(DemoApp.class, args).get("/", c -> {
            c.redirect("/doc/api.html");
        });
    }
}
