package com.egfavre;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Spark.init();

        //Create a GET route for "/" and a POST route for "/create-user" and "/create-message".
        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap allMessages = new HashMap<>();
                    return new ModelAndView(allMessages, "index.html");
                }),
                new MustacheTemplateEngine()
        );


    }
}
