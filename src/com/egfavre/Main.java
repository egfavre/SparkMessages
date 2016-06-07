package com.egfavre;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static HashMap<String, String> users = new HashMap<>();
    static HashMap allMessages = new HashMap<>();

    public static void main(String[] args) {
        Spark.init();

        //Create a GET route for "/" and a POST route for "/create-user" and "/create-message".
        Spark.get(
            "/",
            ((request, response) -> {
                return new ModelAndView(allMessages, "index.html");
            }),
            new MustacheTemplateEngine()
        );

        Spark.post(
              "/create-user",
                ((request, response) -> {
                    String name = request.queryParams("newName");
                    String password = request.queryParams("newPassword")
                    users.put(name, password);
                    allMessages.put(name, null);
                    response.redirect("/create-message");
                    return "";
                })
        );

        Spark.post(
                "/create-message",
                ((request, response) -> {
                    ArrayList<String> messages = new ArrayList<String>();
                    String text = request.queryParams("newMessage");
                    messages.add(text);
                    response.redirect("/logout");
                    return "";
                })
        );


    }
}
