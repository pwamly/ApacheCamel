package com.in28minitus.microservices.camel_microservices_a.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {

    // @Override
    // public void configure() throws Exception {
    // //timer
    // from("timer:active-mq-timer?period=10000")
    // .transform().constant("my message for active Mq")
    // .log("${body}")
    // .to("activemq:my-activemq-queue");
    // //que
    // }

    @Override
    public void configure() throws Exception {
    //timer
    from("file:/home/stephano/Desktop/ApacheCamel/files/json")
    .log("${body}")
    .to("activemq:my-activemq-xml-queue");
    //que
    }
    
}
