package com.rama.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rama.dto.UpdateDTO;
import com.rama.dto.UserDTO;
import com.rama.models.User;
import com.rama.repository.UserRepository;
import com.rama.utils.EmailUtils;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	EmailUtils emailUtils;
	Random rand = new Random();

	@Override
	public String generateRandomPassword() {
		String aToZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234";

		StringBuilder res = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int randIndex = rand.nextInt(aToZ.length());
			res.append(aToZ.charAt(randIndex));
		}
		return res.toString();
	}

	@Override
	public UserDTO getUserwithEmailAndPassword(String email, String password) {
		User userbyEmailAndPassword = userRepo.findByEmailAndPassword(email, password);
		if (userbyEmailAndPassword == null)
			return null;
		else {
			UserDTO userDTO = new UserDTO();
			userDTO.setEmail(email);
			userDTO.setId(userbyEmailAndPassword.getId());
			userDTO.setName(userbyEmailAndPassword.getName());
			userDTO.setPhoneNumber(userbyEmailAndPassword.getPhoneNumber());
			userDTO.setVerfied(userbyEmailAndPassword.isVerfied());
			return userDTO;
		}
	}

	@Override
	public boolean isVerifiedUser(UserDTO user) {

		return user.isVerfied();
	}

	@Override
	public String getQuote(String url) {

		return "qutoes to be loaded by rest template ";
	}

	@Override
	public User registerUser(UserDTO userDto) throws MessagingException {
		String randomPassword = this.generateRandomPassword();
		User user = new User();
		user.setPassword(randomPassword);
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setCity(userDto.getCity());
		user.setCountry(userDto.getCountry());
		user.setState(userDto.getState());
		user.setVerfied(userDto.isVerfied());

		User savedUser = userRepo.save(user);

		emailUtils.sendConfirmationEmail(user.getEmail(), "Registration successful, please update your password",
				"Your old password is : " + savedUser.getPassword());

		return savedUser;

	}

	@Override
	public boolean isAnyRegisteredUser(String email) {
		User userByEmail = userRepo.findByEmail(email);
		return userByEmail != null;
	}

	@Override
	public boolean updatePassword(UpdateDTO updateDTO) {
		UserDTO userwithEmailAndPassword = this.getUserwithEmailAndPassword(updateDTO.getEmail(),
				updateDTO.getOldPass());
		if (userwithEmailAndPassword == null) {
			return false;
		}
		User user = userRepo.findByEmailAndPassword(updateDTO.getEmail(), updateDTO.getOldPass());
		user.setPassword(updateDTO.getNewPass());
		user.setVerfied(true);
		userRepo.save(user);
		return true;
	}

}
