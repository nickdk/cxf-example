package com.xti.nickdk.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xti.nickdk.resources.Country;


@Component
@Produces("application/json")
@Path("/country")
public class CountryRestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryRestService.class);

	@GET
	@Path("/{id}") 
	public Country getCountry(@PathParam("id") String id) {
		LOGGER.info("Calling country service for ID {}", id);
		
		Country country = new Country(id);
		country.setName("Belgium");
		return country;
	}
}
