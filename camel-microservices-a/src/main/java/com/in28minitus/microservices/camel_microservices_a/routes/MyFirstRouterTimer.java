package com.in28minitus.microservices.camel_microservices_a.routes;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyFirstRouterTimer  extends RouteBuilder{

    @Autowired
    private GetCurrentBean getCurrentTime1;

    @Autowired
    private SimpleLoggingProcessing proccessingLog;

    @Override
    public void configure() throws Exception {
         from("timer:first-timer")// taking data from que null
        //  .transform().constant("My Constant Mesage")
        //  .transform().constant("Today's date is " + LocalDateTime.now())
         .bean(getCurrentTime1, "getCurrentTime")
         .bean(proccessingLog)
         .log("${body}")
         .to("log:first-timer");// sending to db  
        // processing - doing anything that does not change the incoming body
        // transformation - doing things that modifying message body
    }
}

@Component
class GetCurrentBean {
    public String getCurrentTime(){
        return "Today's date is " + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessing {
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessing.class);
    public void process(String message){
        logger.info("SimpleLoggingProcessing {}", message);

    }
}
