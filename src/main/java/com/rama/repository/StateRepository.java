package com.rama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rama.models.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	
	List<State> findByCountryId(Integer id);

}
