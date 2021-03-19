package com.SmartContactManager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SmartContactManager.dao.UserRepositiory;
import com.SmartContactManager.entities.User;
import com.SmartContactManager.utility.Message;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepositiory userRepositiory;

	public HomeController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");
		System.out.println(userRepositiory);
		return "home";
	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";
	}

	@GetMapping("/signup")
	public String signUp(Model model ) {
		model.addAttribute("title", "SignUp - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/doRegister")
	public String doRegister(@Valid @ModelAttribute(value = "user") User user, BindingResult result,
			Model model, @RequestParam(value="agreement",defaultValue = "false") boolean agreement) {

		try {
			
			if(!agreement) {
				
				System.out.println("you have not agreed to the terms and conditions");
				throw new Exception("you have not agreed to the terms and conditions");
			}
			
			if(result.hasErrors()) {
				System.out.println("erros : "+result.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			user.setEnabled(true);
			user.setRole("user");
			userRepositiory.save(user);
			model.addAttribute("message", new Message("Successfully registered", "alert-success"));
			model.addAttribute("user",new User());
			return "signup";
			
		} catch (Exception e) {
			model.addAttribute("message", new Message("Something went wrong - " + e.getMessage(), "alert-danger"));
			model.addAttribute("user", user);
			System.out.println(user);
         	e.printStackTrace();
			

			return "signup";
		}
		
		
		
	}

}
