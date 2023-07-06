package com.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dao.UserDAO;
import com.models.User;

@Controller
public class Users {
	@Autowired
	private UserDAO dao;

	@GetMapping("/")
	public String index(ModelMap model) {
		model.addAttribute("users", dao.getUsers());
		model.addAttribute("user", new User());
		return "index";
	}

	@PostMapping(value = "/")
	public String indexPost(@ModelAttribute User user, ModelMap model) {
		if (user.getId() == 0) {
			dao.saveUser(user);
		} else if (Objects.isNull(user.getName())) {
			dao.removeUser((long) user.getId());
		} else {
			dao.updateUser(user, user.getName(), user.getStatus());
		}
		model.addAttribute("users", dao.getUsers());
		model.addAttribute("user", new User());
		return "index";
	}
}