package com.javawebspringboot.websitemovie.controller.web;

import org.springframework.boot.web.servlet.error.ErrorController;

public class ExceptionController implements ErrorController {

//	@RequestMapping("/403")
//	public String error_403() {
//		return "redirect:/loginHandler";
//	}
//	
//	@RequestMapping("/error")
//	public String error() {
//		return "redirect:/loginHandler";
//	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	
}
