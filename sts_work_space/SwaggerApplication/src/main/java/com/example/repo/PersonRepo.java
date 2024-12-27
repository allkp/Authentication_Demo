package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {

}
