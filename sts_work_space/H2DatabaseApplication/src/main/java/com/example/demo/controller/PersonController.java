package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PersonDto;
import com.example.demo.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@GetMapping
	public List<PersonDto> getAllPersons() {
		return service.getAllPersons();
	}
	
	@GetMapping("/{id}")
	public PersonDto getPersonById(@PathVariable int id) {
		return service.getPersonById(id);
	}
	
	@PostMapping
	public PersonDto createPerson(@RequestBody PersonDto dto) {
		return service.createPerson(dto);
	}
	
	@PutMapping("/{id}")
	public PersonDto updatePerson(@PathVariable int id, @RequestBody PersonDto dto) {
		return service.updatePerson(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void deletePerson(@PathVariable int id) {
		service.deletePerson(id);
		System.out.println(getAllPersons());
	}
}