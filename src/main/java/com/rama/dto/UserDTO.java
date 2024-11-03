package com.rama.dto;

import lombok.Data;

@Data
public class UserDTO {
	Integer id;
	String name;
	String email;
	String phoneNumber;
	boolean isVerfied;
	String country;
	String state;
	String city;
}
