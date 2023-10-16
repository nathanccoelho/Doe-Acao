package com.doeacao.doeacao.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.doeacao.doeacao.repository.ThemeRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<User> email = userRepository.findByUser(userName);
		
		if(email.isPresent())
			return new UserDetailsImpl(email.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			
	}

	}



