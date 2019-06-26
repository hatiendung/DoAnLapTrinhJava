package com.javawebspringboot.websitemovie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.websitemovie.model.Actor;

@Repository
@Transactional
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	Actor findByCodeActor(String codeActor);

	Actor findActorByCodeActor(String codeActor);

	
	@Query("SELECT a FROM Actor a where a.nameActor LIKE %?1%")
	List<Actor> searchActor(String nameActor);
}
