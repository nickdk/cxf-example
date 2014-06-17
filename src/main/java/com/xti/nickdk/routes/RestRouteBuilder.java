package com.xti.nickdk.routes;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RestRouteBuilder extends RouteBuilder {
	@Override
	public void configure() throws Exception {

		String port = System.getProperty("PORT");
		if(port == null) {
			port = "9090";
		}

		String hostName = "0.0.0.0";
		
		from("cxfrs:http://"+hostName+":"+port+"?resourceClasses=com.xti.nickdk.rest.CountryRestService&bindingStyle=SimpleConsumer")
		.recipientList(simple("direct:${header.operationName}"));
		
		from("direct:getCountry")
		.beanRef("countryService", "getCountry(${header.id}")
		.marshal().json(JsonLibrary.Jackson);
		
	}
}