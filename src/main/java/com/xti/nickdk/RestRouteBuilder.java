package com.xti.nickdk;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RestRouteBuilder extends RouteBuilder {
   @Override
   public void configure() throws Exception {
         from("cxfrs:http://0.0.0.0:9090?resourceClasses=com.xti.nickdk.CountryService&bindingStyle=SimpleConsumer")
               .marshal().json(JsonLibrary.Jackson);
   }
}