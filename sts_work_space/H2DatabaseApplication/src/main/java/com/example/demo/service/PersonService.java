package com.example.demo.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PersonDto;
import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepo;


@Service
public class PersonService{
	
//	@Autowired
//	private Person model;
	
	@Autowired
	private PersonRepo repo;
	
	//Entity to Dto Convertion
	public PersonDto convertToDto(Person person) {
		return new PersonDto(person.getId(), person.getName(), person.getAge(), person.getContact());
	}
	
	//Dto to Entity Convertion
	public Person convertToEntity(PersonDto dto) {
		return new Person(dto.getId(), dto.getName(), dto.getAge(), dto.getContact());
	}
	
	public List<PersonDto> getAllPersons(){
		return repo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	public PersonDto getPersonById(int id) {
		return repo.findById(id).map(this::convertToDto).orElse(null);
	}
	
	public PersonDto createPerson(PersonDto dto) {
		Person person = convertToEntity(dto);
		Person savedPerson = repo.save(person);
		return convertToDto(savedPerson);
	}
	
	public PersonDto updatePerson(int id, PersonDto dto) {
		return repo.findById(id).map(existPerson -> {
			existPerson.setAge(dto.getAge());
			existPerson.setName(dto.getName());
			existPerson.setContact(dto.getContact());
			Person updatePerson = repo.save(existPerson);
			return convertToDto(updatePerson);
		}).orElse(null);
	}
	
	public void deletePerson(int id) {
		repo.deleteById(id);
	}
	
	

}
