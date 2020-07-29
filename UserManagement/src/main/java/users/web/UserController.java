package users.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import users.data.UserRepository;
import users.model.SortCriteria;
import users.model.User;



/*
 * UserController is the controller responsible for handling requests to the /users endpoint
 * It handles the logic for fetching the list with users which can then be sorted
 */
@Controller
@RequestMapping({"/users"})
public class UserController {

	private UserRepository userRepo;
	
	
	@Autowired
	public UserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	

	@GetMapping
	public String showAllUsers(Model model) {
		
		// Fetch all the currently present users from the database,
		// so that they can be rendered on the page
		List<User> usersList = new ArrayList<>();
		userRepo.findAll().forEach(i -> usersList.add(i));
		
		model.addAttribute("users", usersList);
		return "users";
	}
	
	
	@PostMapping
	public String sortUsers(Model model, SortCriteria sortCrit, Errors errors) {
		
		// Check if the user's input is valid
		if (errors.hasErrors()) {
			return "users";
		}
		
		// Retrieve all users from the database and sort them based on the sort criteria chosen by the user
		List<User> filteredUsers = new ArrayList<>();
		filteredUsers = userRepo.findAll(Sort.by(Sort.Direction.fromString(sortCrit.getOrder()),sortCrit.getSortBy()));
		model.addAttribute("users",filteredUsers);
		
		return "users";
	}
}
