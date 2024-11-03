package com.rama.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.rama.repository.CityRepository;
import com.rama.repository.CountryRepository;
import com.rama.repository.StateRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ListServiceImpl implements ListService {
	CountryRepository countryRepo;
	StateRepository stateRepo;
	CityRepository cityRepo;

	@Override
	public Map<Integer, String> getCountries() {
		return countryRepo.findAll().stream()
				.collect(Collectors.toMap(country -> country.getId(), country -> country.getCountryName()));

	}

	@Override
	
	public Map<Integer, String> getStates(@PathVariable(name = "id") Integer countryId) {
		return  stateRepo.findByCountryId(countryId).stream()
				.collect(Collectors.toMap(state -> state.getStateId(), state -> state.getStateName()));
	
	}

	@Override
	
	public Map<Integer, String> getCities(@PathVariable(name = "id") Integer stateId) {
		return  cityRepo.findByStateStateId(stateId).stream().collect(Collectors.toMap(city->city.getCityId(), city->city.getCityName()));
		
	}

}
