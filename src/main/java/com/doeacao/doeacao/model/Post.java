package com.doeacao.doeacao.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "tb_posts")
public class Post {
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "O atributo title é obrigatório!")
	@Size (min=2, max =255, message = "O atributo title deve conter no mínimo 2 e no máximo 255 catacteres!")
	private String title;
	
	@NotBlank (message = "O atributo content é obrigatório!")
	@Size (min=2, max =5000, message = "O atributo content deve conter no mínimo 2 e no máximo 5000 catacteres!")
	private String content;
	
	@Size (min=5, max =5000, message = "O atributo pic deve conter no mínimo 5 e no máximo 5000 catacteres!")
	private String pic;
	
	@Size (min=2, max =5001, message = "O atributo comment deve conter no mínimo 2 e no máximo 5000 catacteres!")
	private String comment;
	
	
	private Long likes;
	
	
	@UpdateTimestamp
	private LocalDateTime date;
	
	
	@ManyToOne
	@JsonIgnoreProperties("post")
	private Theme theme;
	
	@ManyToOne
	@JsonIgnoreProperties("post")
	private User user;


	//Getters and Setters;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getPic() {
		return pic;
	}


	public void setPic(String pic) {
		this.pic = pic;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Long getLikes() {
		return likes;
	}


	public void setLike(Long likes) {
		this.likes = likes;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public Theme getTheme() {
		return theme;
	}


	public void setTheme(Theme theme) {
		this.theme = theme;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
	