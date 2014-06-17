package com.xti.nickdk.service;

import org.springframework.stereotype.Service;

import com.xti.nickdk.resources.Country;

@Service("countryService")
public class CountryServiceImpl implements CountryService{

	public Country getCountry(String id) {
		Country country = new Country(id);
		country.setName("Belgium");
		return country;
	}

}
