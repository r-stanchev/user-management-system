package users.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import users.data.UserRepository;
import users.model.User;


/*
 * AddUserController is the controller responsible for handling requests to the /add-user endpoint
 * It handles the logic for checking if a user already exists and the addition of a new user
 */
@Controller
@RequestMapping("/add-user")
public class AddUserController {
	
	private UserRepository userRepo;
	
	
	@Autowired
	public AddUserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	@GetMapping
	public String loadPage(Model model) {
		model.addAttribute("user", new User());
		return "add-user";
	}
	
	
	@PostMapping
	public String addNewUser(@Valid User user,  Errors errors, Model model) {
		
		// Check if the user's input is valid
		if (errors.hasErrors()) {
			return "add-user";
		}
		
		// First check if the user already exists
		if (!userRepo.existsByEmail(user.getEmail())) {
			userRepo.save(user);
		} else {
			errors.rejectValue("email", "taken-email","Email already taken");
			return "add-user";
		}
		
		return "redirect:/users";
	}
}
