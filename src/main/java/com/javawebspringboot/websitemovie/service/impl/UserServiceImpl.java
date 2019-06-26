package com.javawebspringboot.websitemovie.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javawebspringboot.websitemovie.model.Role;
import com.javawebspringboot.websitemovie.model.User;
import com.javawebspringboot.websitemovie.repository.RoleRepository;
import com.javawebspringboot.websitemovie.repository.UserRepository;
import com.javawebspringboot.websitemovie.service.UserService;

@Service
@Transactional
public class UserServiceImpl  implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	@Override
	public boolean registerAccount(String email, String password) {
		if (userRepository.findByEmail(email) == null) {
			User userRegister = new User();
			userRegister.setDatetimeCreatedAccount(LocalDateTime.now());
			userRegister.setEmail(email);
			userRegister.setPassword(passwordEncoder.encode(password));
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepository.findByRoleName("ROLE_USER"));
			userRegister.setRoleList(roles);
			userRepository.save(userRegister);
			return true;
		}
		return false;
	}

}
