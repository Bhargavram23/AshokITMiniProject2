package com.rama.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer stateId;
	String stateName;
	@ManyToOne
	Country country;

}
