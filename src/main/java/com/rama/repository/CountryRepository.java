package com.rama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rama.models.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
