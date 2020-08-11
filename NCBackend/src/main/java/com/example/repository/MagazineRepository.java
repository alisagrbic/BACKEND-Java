package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {
	
	@Query("SELECT m FROM Magazine m WHERE m.title  = :title")
	Magazine findByTitle(@Param("title") String title);
	
	@Query("SELECT u FROM Magazine u WHERE u.id  = :id")
	Magazine findById(@Param("id") long id);
}
