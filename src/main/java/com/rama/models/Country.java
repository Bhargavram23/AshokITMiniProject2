package com.rama.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Country {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
Integer id;
String countryName;
}
