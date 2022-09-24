package com.nnk.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class is the controller class for the Home page
 * 
 * @author Rémi Deronzier
 */
@Controller
public class HomeController {

	/**
	 * @param model
	 * @return String
	 */
	@RequestMapping("/")
	public String home(Model model) {
		return "home";
	}

	/**
	 * @param model
	 * @return String
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		return "redirect:/bidList/list";
	}

}
