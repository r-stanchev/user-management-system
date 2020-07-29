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
import org.springframework.web.bind.annotation.SessionAttributes;

import users.data.UserRepository;
import users.model.User;


/*
 * RetrieveUserController is the controller responsible for handling requests to the /retrieve-user endpoint
 * It handles the logic for fetching a user entry from the database so that it's fields can be updated
 * 
 * The user that will be extracted from the database is stored also in the form of a sessionAttribute,
 * so that it can be accessed by the edit-user page
 */
@Controller
@RequestMapping("/retrieve-user")
@SessionAttributes("editableUser")
public class RetrieveUserController {
	
	private UserRepository userRepo;
	
	
	@Autowired
	public RetrieveUserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	@GetMapping
	public String loadPage(Model model) {
		model.addAttribute("user", new User());
		return "retrieve-user";
	}
	
	
	@PostMapping
	public String retrieveUser(@ModelAttribute @Valid User user, Errors errors, Model model) {
		
		// Check if the user's input is valid
		if (errors.hasErrors()) {
			return "retrieve-user";
		}
		
		// First check if the user already exists
		String userEmail = user.getEmail();
		if (userRepo.existsByEmail(userEmail)) {
			User editableUser = userRepo.findByEmail(userEmail);
			model.addAttribute("editableUser", editableUser);
		} else {
			errors.rejectValue("email","email-not-found","Email not found");
			return "retrieve-user";
		}
		
		return "redirect:/edit-user";
	}
}
