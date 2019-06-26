package com.javawebspringboot.websitemovie.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javawebspringboot.websitemovie.model.Director;

@Repository
@Transactional
public interface DirectorRepository extends JpaRepository<Director, Integer> {
	@Query("SELECT a FROM Director a where a.nameDirector LIKE %?1%")
	List<Director> searchDirector(String nameDirector);
}
