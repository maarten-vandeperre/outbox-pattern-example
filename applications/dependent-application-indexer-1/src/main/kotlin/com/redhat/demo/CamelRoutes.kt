// TODO uncomment this file if you want to make use of the camel integration on startup
//package com.redhat.demo
//
//import jakarta.enterprise.context.ApplicationScoped
//import org.apache.camel.Exchange
//import org.apache.camel.builder.RouteBuilder
//
//// kamel init RouteFile.java
//// kamel run RouteFile.java
//
//@ApplicationScoped
//class CamelRoutes : RouteBuilder() {
//    override fun configure() {
//        from("timer://runOnce?repeatCount=1&delay=5000")
//            .routeId("reindex")
//            .setBody(constant("SELECT firstname, lastname, birthdate FROM person LIMIT 5 OFFSET 0"))
//            .to("jdbc:default")
//            .to("log:info")
//            .bean(this, "process")
//        //TODO to MongoDB or to HTTP POST endpoint of dependent application
//    }
//
//    fun process(exchange: Exchange){
//        val body = exchange.`in`.body as ArrayList<Map<String, String>>
//        println(body[0]["firstname"])
//    }
//}