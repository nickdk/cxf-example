package com.xti.nickdk.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.xti.nickdk.entities.Country;
import com.xti.nickdk.repositories.CountryRepository;
import com.xti.nickdk.resources.CountryDto;

@Component
@Path("/countries")
@Produces("application/json")
@Consumes("application/json")
public class CountryRestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryRestService.class);

	@Autowired
	private CountryRepository countryRepository;
	
	@GET
	@Path("/{id}") 
	public CountryDto getCountry(@PathParam("id") String id) {
		LOGGER.info("Retrieving country with id: " +id);
		Country country = countryRepository.findOne(id);
		return toDto(country);
	}
	
	@GET
	@Path("/") 
	public List<CountryDto> getCountries() {
		List<Country> countries = countryRepository.findAll();
		return toDtos(countries);
	}
	
	@POST
	@Path("/") 
	public CountryDto createCountry(CountryDto countryDto) {
		LOGGER.info("Creating country with name: " +countryDto.getName());
		Country country = new Country();
		country.setName(countryDto.getName());
		countryRepository.save(country);
		LOGGER.info("Persisted country, id: " +country.getId());
		return getCountry(country.getId());
	}
	
	private CountryDto toDto(Country country) {
		return toDtoFunction().apply(country);
	}
	
	private List<CountryDto> toDtos(List<Country> countries) {
		return Lists.transform(countries, toDtoFunction());
	}
	
	private Function<Country, CountryDto> toDtoFunction() {
		return 	new Function<Country, CountryDto>() {
			public CountryDto apply(Country country) {
				CountryDto countryDto = new CountryDto();
				countryDto.setId(country.getId());
				countryDto.setName(country.getName());
				return countryDto;
			}
		};
	}

}
