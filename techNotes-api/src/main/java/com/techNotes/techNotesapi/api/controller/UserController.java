package com.techNotes.techNotesapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techNotes.techNotesapi.api.dto.UserDTO;
import com.techNotes.techNotesapi.exception.BusinessRulesException;
import com.techNotes.techNotesapi.model.entity.User;
import com.techNotes.techNotesapi.model.entity.enums.Role;
import com.techNotes.techNotesapi.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService service;
	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		List<User> listUsers=service.getAllUsers();
		
		return ResponseEntity.ok(listUsers);
	}
	
	@PostMapping
	public ResponseEntity<?> createNewUser(@RequestBody UserDTO dto){
		System.out.println(dto);
		User user=convert(dto);
		try {
			user=service.createNewUser(user);
			
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		}catch (BusinessRulesException e) {
			return ResponseEntity.badRequest().body(e.getMessage());					
		}
	}

	

	private User convert(UserDTO dto) {
		return User.builder().id(dto.getId())
				.username(dto.getUsername())
				.password(dto.getPassword())
				.role(Role.valueOf(dto.getRole()))
				.active(dto.getActive()).build();
	}
	
	
}