package com.in28minitus.microservices.camel_microservices_a.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        //  from("file:files2/input")
        //  .log("file contnts + ${body}")
        // .to("file:files/output");

         from("file:/home/stephano/Desktop/ApacheCamel/files/json")
         .routeId("Files-Input-Route")
         .transform().body(String.class)
         .choice()
            .when(simple("${file:ext} ends with 'xml'"))
                .log("XML file")
            .when(simple("${body} contains 'USD'"))
                .log("Not XML file but has USD")
            .otherwise()
                .log("Not XML")
         .end()
         .to("direct:log-file-values")
        .to("file:files/output");


        from("direct:log-file-values")
        .log("Below: are logs:")
        .log("${messageHistory} ${file:absolute.path}")
        .log("${file:name} ${file:name.ext}")
        .log("${file:size}");


    }
    
}
