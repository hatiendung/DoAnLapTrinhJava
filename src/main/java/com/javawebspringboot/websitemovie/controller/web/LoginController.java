package com.javawebspringboot.websitemovie.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String loginPage() {
		return "redirect:/";
	}

	@RequestMapping("/loginHandler")
	public String login() {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (userDetails.getAuthorities().toString().equals("[ROLE_ADMIN, ROLE_USER]")) {
			return "redirect:/admin";
		}

		if (userDetails.getAuthorities().toString().equals("[ROLE_USER]")) {
			return "redirect:/user";
		}
		return "redirect:/";

	}

	@RequestMapping(value = "/dang-xuat", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	

}
