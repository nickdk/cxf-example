package com.xti.nickdk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xti.nickdk.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

}
