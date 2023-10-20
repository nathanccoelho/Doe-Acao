package com.doeacao.doeacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doeacao.doeacao.model.User;
import com.doeacao.doeacao.model.UserLogin;
import com.doeacao.doeacao.repository.UserRepository;
import com.doeacao.doeacao.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll(){
		
		return ResponseEntity.ok(userRepository.findAll());
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		return userRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLogin> autenticarUser(@RequestBody Optional<UserLogin> userLogin){
		
		return userService.authenticateUser(userLogin)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> postUser(@RequestBody @Valid User user) {

		return userService.registerUser(user)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	@PutMapping("/update")
	public ResponseEntity<User> putUser(@Valid @RequestBody User user) {	
		
		return userService.updateUser(user)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}

}
