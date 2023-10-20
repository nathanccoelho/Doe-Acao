package com.doeacao.doeacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.doeacao.doeacao.model.Theme;
import com.doeacao.doeacao.repository.ThemeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/themes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@GetMapping
	public ResponseEntity<List <Theme>> getAll() {
		return ResponseEntity.ok(themeRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Theme> getById(@PathVariable Long id) {
		return themeRepository.findById(id)
				.map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/description/{description}")
	public ResponseEntity<List<Theme>> getByTitle (@PathVariable String description) {
		return ResponseEntity.ok(themeRepository.findAllByDescriptionContainingIgnoreCase(description));
	}
	
	@PostMapping
	public ResponseEntity<Theme> post(@Valid @RequestBody Theme theme){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(themeRepository.save(theme));
	}
	
	@PutMapping
	public ResponseEntity<Theme> put(@Valid @RequestBody Theme theme) {
		return themeRepository.findById(theme.getId())
				.map(response -> ResponseEntity.status(HttpStatus.CREATED)
				.body(themeRepository.save(theme)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Theme> theme = themeRepository.findById(id);
		
		if (theme.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		themeRepository.deleteById(id);
	}
	
}
