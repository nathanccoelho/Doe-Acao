package com.doeacao.doeacao.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "tb_tema")
public class Theme {

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "O atributo description é obrigatório!")
	@Size (min=2, max =255, message = "O atributo description deve conter no mínimo 2 e no máximo 255 catacteres!")
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "theme", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("theme")
	private List<Post> post;

	
	
	//Getters and Setters;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	
	
	
}