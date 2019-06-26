package com.javawebspringboot.websitemovie.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.javawebspringboot.websitemovie.model.Role;
import com.javawebspringboot.websitemovie.model.User;
import com.javawebspringboot.websitemovie.repository.RoleRepository;
import com.javawebspringboot.websitemovie.repository.UserRepository;

@Component
public class WebUtil implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		
		if (roleRepository.findByRoleName("ROLE_USER") ==null) {
			Role role = new Role("ROLE_USER", null);
			roleRepository.save(role);
		}
		
		if (roleRepository.findByRoleName("ROLE_ADMIN") ==null) {
			Role role = new Role("ROLE_ADMIN", null);
			roleRepository.save(role);
		}
		
		

		if (userRepository.findByEmail("admin@gmail.com") == null) {
			User admin = new User();
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("admin"));
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepository.findByRoleName("ROLE_ADMIN"));
			roles.add(roleRepository.findByRoleName("ROLE_USER"));
			admin.setRoleList(roles);
			userRepository.save(admin);
		}
		
		if (userRepository.findByEmail("user2@gmail.com") == null) {
			User user = new User();
			user.setEmail("user2@gmail.com");
			user.setPassword(passwordEncoder.encode("user2"));
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepository.findByRoleName("ROLE_USER"));
			user.setRoleList(roles);
			
			userRepository.save(user);
			
		}


	}

}
