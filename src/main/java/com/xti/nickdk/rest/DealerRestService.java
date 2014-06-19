package com.xti.nickdk.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.xti.nickdk.resources.Dealer;


@Component
@Produces("application/json")
@Path("/dealer")
public class DealerRestService {

	@GET
	@Path("/{id}") 
	public Dealer getDealer(@PathParam("id") String id) {
		Dealer dealer = new Dealer(id);
		dealer.setName("Toyota City");
		return dealer;
	}
}
