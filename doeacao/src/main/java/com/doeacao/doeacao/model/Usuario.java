package com.doeacao.doeacao.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
public class Usuario {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "O atributo name é obrigatório!")
	@Size (min=2, max =255, message = "O atributo name deve conter no mínimo 2 e no máximo 255 catacteres!")
	private String name;
	
	@NotBlank (message = "O atributo email é obrigatório!")
	@Email
	private String email;
	
	
	@CPF
	private String cpf;
	
	@CNPJ
	private String cnpj;
	
	@NotBlank (message = "O atributo password é obrigatório!")
	@Size (min=8, max =12, message = "O atributo password deve conter no mínimo 8 e no máximo 12 catacteres!")
	private String password;
	
	@Size (min=5, max =5000, message = "O atributo pic deve conter no mínimo 5 e no máximo 5000 catacteres!")
	private String pic;
	
	@NotNull
	private LocalDate birthDate;


	
	//Getters and Setters;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPic() {
		return pic;
	}


	public void setPic(String pic) {
		this.pic = pic;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	
	
	

}
