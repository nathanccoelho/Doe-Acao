package com.doeacao.doeacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doeacao.doeacao.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByUser(@Param("user") String user);

}
