package com.xti.nickdk;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CountryService {

	@GET
	@Path("country/{id}")
	public Country getCountry(@PathParam("id") String id) {
		Country country = new Country();
		country.setName("Belgium");
		return country;
	}
}
