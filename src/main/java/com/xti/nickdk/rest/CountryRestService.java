package com.xti.nickdk.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xti.nickdk.entities.Country;
import com.xti.nickdk.repositories.CountryRepository;
import com.xti.nickdk.resources.CountryDto;


@Component
@Path("/countries")
@Produces("application/json")
@Consumes("application/json")
public class CountryRestService {

	@Autowired
	private CountryRepository countryRepository;
	
	@GET
	@Path("/{id}") 
	public CountryDto getCountry(@PathParam("id") String id) {
		Country country = countryRepository.findOne(id);
		if(country != null) {
			CountryDto countryDto = new CountryDto();
			countryDto.setId(country.getId());
			countryDto.setName(country.getName());
			return countryDto;
		} else {
			return null;
		}
	}
	
	@POST
	@Path("/") 
	public CountryDto createCountry(CountryDto countryDto) {
		Country country = new Country();
		country.setName(countryDto.getName());
		countryRepository.save(country);
		return getCountry(country.getId());
	}
}
