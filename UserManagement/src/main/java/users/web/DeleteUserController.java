package users.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import users.data.UserRepository;
import users.model.User;


/*
 * DeleteUserController is the controller responsible for handling requests to the /delete-user endpoint
 * It handles the logic for checking if a user exists and the deletion of the user in question
 */
@Controller
@RequestMapping("/delete-user")
public class DeleteUserController {
	
	private UserRepository userRepo;
	
	
	@Autowired
	public DeleteUserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	@GetMapping
	public String loadPage(Model model) {
		model.addAttribute("user", new User());
		return "delete-user";
	}
	
	
	@PostMapping
	public String deleteUser(@ModelAttribute @Valid User user, Errors errors, Model model) {
		
		// Check if the user's input is valid
		if (errors.hasErrors()) {
			return "delete-user";
		}
		
		// First check if the user already exists
		String userEmail = user.getEmail();
		if (userRepo.existsByEmail(userEmail)) {
			userRepo.deleteByEmail(userEmail);
		} else {
			errors.rejectValue("email","email-not-found","Email not found");
			return "delete-user";
		}
		
		return "redirect:/users";
	}
}
