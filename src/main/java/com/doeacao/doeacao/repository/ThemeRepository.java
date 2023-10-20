package com.doeacao.doeacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.doeacao.doeacao.model.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>{
	
	
	public List<Theme> findAllByDescriptionContainingIgnoreCase (@Param("description") String description);

}
