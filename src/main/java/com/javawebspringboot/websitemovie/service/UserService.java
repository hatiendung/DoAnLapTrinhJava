package com.javawebspringboot.websitemovie.service;

import java.util.List;

import com.javawebspringboot.websitemovie.model.User;

public interface UserService {
	
	User findByEmail(String email);

	List<User> findAllUser();

	boolean registerAccount(String email, String password);

}
