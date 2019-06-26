package com.javawebspringboot.websitemovie.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawebspringboot.websitemovie.model.Role;

@Repository
@Transactional
public interface RoleRepository  extends JpaRepository<Role, Integer>{

	
	Role findByRoleName(String roleName);
}
