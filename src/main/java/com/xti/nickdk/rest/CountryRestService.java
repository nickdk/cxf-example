package com.xti.nickdk.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.xti.nickdk.resources.Country;


@Component
@Produces("application/json")
public class CountryRestService {

	@GET
	@Path("/{id}") 
	public Country getCountry(@PathParam("id") String id) {
		Country country = new Country(id);
		country.setName("Belgium");
		return country;
	}
}
