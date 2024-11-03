package com.rama.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rama.service.ListService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CountryDetailsController {

	ListService listService;

	@GetMapping("/country")
	public Map<Integer, String> getCountries() {
		return listService.getCountries();

	}

	@GetMapping("/states/{id}")
	public Map<Integer, String> getStates(@PathVariable(name = "id") Integer countryId) {

		return listService.getStates(countryId);
	}

	@GetMapping("/cities/{id}")
	public Map<Integer, String> getCities(@PathVariable(name = "id") Integer stateId) {
		return listService.getCities(stateId);
	}
}
