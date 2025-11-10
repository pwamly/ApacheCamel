package com.in28minitus.microservices.camel_microservices_a.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestApiConsumerRouter extends RouteBuilder{

    @Override
    public void configure() throws Exception {
         restConfiguration().host("localhost").port("9090");

         from("timer:rest-api-consumer?period=1000")
         .setHeader("from",()->"EURO")
         .setHeader("to",()->"TZS ")
         .log("rest api + ${body}")
        .to("rest:get:/currency-exchange/from/{from}/to/{to} ")
        .log("${body}");



    }
    
}
