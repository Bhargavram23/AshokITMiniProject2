package com.rama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.rama.dto.LoginDTO;
import com.rama.dto.UpdateDTO;
import com.rama.dto.UserDTO;
import com.rama.service.UserServiceImpl;

import jakarta.mail.MessagingException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ResponseDTO{
	int id;
	String quote;
	String author;
}
@Controller
public class UserController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	RestTemplate restTemplate;

	@GetMapping(path={"/login","/"})
	public String loadLoginPage(Model model) {
		model.addAttribute("login_DTO", new LoginDTO());
		return "login";
	}

	@PostMapping("/login")
	public String handleloadLoginPageSubmit(@ModelAttribute LoginDTO loginDTO, Model model) {
		String email = loginDTO.getEmail();
		String password = loginDTO.getPassword();
		UserDTO user = userService.getUserwithEmailAndPassword(email, password);
		if (user == null) {
			model.addAttribute("msg", "invalid credentials");
			return "login";
		}
		if (!userService.isVerifiedUser(user)) {
			model.addAttribute("msg", "please modify your password to continue ");
			UpdateDTO updateDTO = new UpdateDTO();
			updateDTO.setEmail(loginDTO.getEmail());
			model.addAttribute("update_DTO", updateDTO);
	
			return "updatePassword";
		}

		return "redirect:/dashboard";
	}

	@GetMapping("/register")
	public String loadRegisterPage(Model model) {
		model.addAttribute("user_DTO", new UserDTO());
		return "register";
	}

	@PostMapping("/register")
	public String handleRegisterPageSubmit(@ModelAttribute UserDTO userDTO, Model model) throws MessagingException {

		if (userService.isAnyRegisteredUser(userDTO.getEmail())) {
			model.addAttribute("msg", "user exists, try loggin in");
			model.addAttribute("login_DTO",new LoginDTO());
			return "login";
		} else {
			userService.registerUser(userDTO);
			model.addAttribute("msg", "an email has been sent, please modify your password");
			UpdateDTO updateDTO = new UpdateDTO();
			updateDTO.setEmail(userDTO.getEmail());
			model.addAttribute("update_DTO", updateDTO);
			return "updatePassword";
		}

	}

	@PostMapping("/update")
	public String handleUpdatePageSubmit(@ModelAttribute UpdateDTO userDTO, Model model) {
		if (userService.updatePassword(userDTO)) {
			return "redirect:dashboard";
		} else {
			model.addAttribute("msg", "something went wrong, give a try later");
			return "updatePassword";
		}
	}

	@GetMapping("/dashboard")
	public String displaydashboardResponse(Model model) {
		ResponseDTO responseDTO = restTemplate.getForEntity("https://dummyjson.com/quotes/random", ResponseDTO.class).getBody();
		model.addAttribute("quote",responseDTO!=null? responseDTO.getQuote():" No quote");
		model.addAttribute("author",responseDTO!=null? responseDTO.getAuthor(): "No Author");
		
		return "dashboard";
	}

}
