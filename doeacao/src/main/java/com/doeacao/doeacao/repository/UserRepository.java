package com.doeacao.doeacao.repository;

import com.doeacao.doeacao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUser(@Param("user")String user);

}
