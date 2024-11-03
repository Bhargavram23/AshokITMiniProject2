package com.rama.service;

import com.rama.dto.UpdateDTO;
import com.rama.dto.UserDTO;
import com.rama.models.User;

import jakarta.mail.MessagingException;

public interface UserService {
	User registerUser(UserDTO user) throws MessagingException;
	String generateRandomPassword();
	UserDTO getUserwithEmailAndPassword(String email,String password);
	boolean isAnyRegisteredUser(String email);
	boolean isVerifiedUser(UserDTO user);
	boolean updatePassword(UpdateDTO update);
	String getQuote(String url);
}
