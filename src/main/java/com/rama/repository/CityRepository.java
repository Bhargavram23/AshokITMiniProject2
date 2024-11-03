package com.rama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rama.models.City;

import java.util.List;


public interface CityRepository extends JpaRepository<City, Integer> {
	List<City> findByStateStateId(Integer stateId);
}
