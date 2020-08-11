package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Scientificfield;

@Repository
public interface ScientificFieldRepository extends JpaRepository<Scientificfield, Long> {
}