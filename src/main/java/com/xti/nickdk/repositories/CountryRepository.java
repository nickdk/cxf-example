package com.xti.nickdk.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xti.nickdk.entities.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {

}
