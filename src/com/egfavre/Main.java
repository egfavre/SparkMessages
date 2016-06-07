package com.egfavre;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static String username;
    static String password;
    static String newAccount;
    static String thisMessage;
    static Message message;
    static ArrayList<Message> messages = new ArrayList<>();
    static HashMap users;
    static boolean validLogin;

    public static void main(String[] args) {
        Spark.init();
        Spark.get(
                "/",
                (request, response) -> {
                    HashMap m = new HashMap();
                    if (message == null) {
                        return new ModelAndView(m, "login.html");
                    }
                    else {
                        m.put("userName", message.username);
                        m.put("messages", messages);
                        return new ModelAndView(m, "writeMessage.html");
                    }
                },
                new MustacheTemplateEngine()
        );
        Spark.post(
                "/login",
                (request, response) -> {
                    username = request.queryParams("userName");
                    message = new Message(username);
                    messages.add(message);
                    response.redirect("/");
                    return "";
                }
        );
        Spark.post(
                "/writeMessage",
                (request, response) -> {
                    thisMessage = request.queryParams("yourMessage");
                    message.yourMessage = thisMessage;
                    messages.add(message);
                    response.redirect("/");
                    return "";
                }
        );
        Spark.post(
                "/logout",
                (request, response) -> {
                    message = null;
                    response.redirect("/");
                    return "";
                }
        );
    }
}
