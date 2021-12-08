package test;

import org.noear.solon.Solon;

public class App {
    public static void main(String[] args) {
        Solon.start(App.class, args).get("/", c -> {
            c.redirect("/static/doc/api.htm");
        });
    }
}
