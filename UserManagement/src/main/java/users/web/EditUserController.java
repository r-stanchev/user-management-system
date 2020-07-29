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
import org.springframework.web.bind.support.SessionStatus;

import users.data.UserRepository;
import users.model.User;


/*
 * EditUserController is the controller responsible for handling requests to the /edit-user endpoint
 * It handles the logic for updating a user's data
 * 
 * The user who's data will be changed is coming from the retrieve-user page/controller in the form
 * of a sessionAttribute
 */
@Controller
@RequestMapping("/edit-user")
@SessionAttributes("editableUser")
public class EditUserController {
	
	private UserRepository userRepo;
	
	
	@Autowired
	public EditUserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	@GetMapping
	public String loadPage() {
		return "edit-user";
	}
	
	
	@PostMapping
	public String updateUser(@ModelAttribute("editableUser") @Valid User editableUser, Errors errors, Model model, SessionStatus sessionStatus) {
		
		// Check if the user's input is valid
		if (errors.hasErrors()) {
			return "edit-user";
		}
		
		// Update the user's data
		userRepo.updateUser(editableUser.getFirstName(),
				editableUser.getLastName(),
				editableUser.getEmail(),
				editableUser.getDateOfBirth(),
				editableUser.getId());
		
		// Clean up the session variable
		sessionStatus.setComplete();
		
		return "redirect:/users";
	}
}
