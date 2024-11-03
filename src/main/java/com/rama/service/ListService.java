package com.rama.service;

import java.util.Map;

public interface ListService {
	Map<Integer,String> getCountries();
	Map<Integer,String> getStates(Integer countryId);
	Map<Integer,String> getCities(Integer stateId);
}
