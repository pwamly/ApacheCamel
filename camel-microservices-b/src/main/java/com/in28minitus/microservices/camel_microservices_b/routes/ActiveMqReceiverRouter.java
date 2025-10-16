package com.in28minitus.microservices.camel_microservices_b.routes;

import java.math.BigDecimal;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {

@Autowired    
private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;

    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
        .bean(myCurrencyExchangeProcessor)
        .to("log:received-message-from-active-mq");
    }

//     {
//     "id":1000,
//     "from":"USD",
//     "to":"INR",
//     "conversionMultiple":70
// }


}
@Component
class MyCurrencyExchangeProcessor{
        Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);

    public void processMessage(CurrencyExchange currencyExchange){

        

        logger.info("Do some proceesing with {}",currencyExchange.getConversionMultiple());

        return;
    }
}