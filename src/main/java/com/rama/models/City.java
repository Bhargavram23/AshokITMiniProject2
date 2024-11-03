package com.rama.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class City {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	Integer cityId;
	String cityName;
	@ManyToOne
	State state;

}
