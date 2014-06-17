package com.xti.nickdk.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public interface CountryRestService {

	@GET
	@Path("country/{id}") 
	public String getCountry(@PathParam("id") String id);
}
