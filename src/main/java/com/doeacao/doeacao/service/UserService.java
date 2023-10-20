package com.doeacao.doeacao.service;

import com.doeacao.doeacao.model.User;
import com.doeacao.doeacao.model.UserLogin;
import com.doeacao.doeacao.repository.UserRepository;
import com.doeacao.doeacao.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Optional<User> registerUser(User user) {

        if (userRepository.findByUser(user.getUser()).isPresent())
            return Optional.empty();

        user.setPassword(encryptPassword(user.getPassword()));

        return Optional.of(userRepository.save(user));

    }

    public Optional<User> updateUser(User user) {

        if(userRepository.findById(user.getId()).isPresent()) {

            Optional<User> searchUser = userRepository.findByUser(user.getUser());

            if ( (searchUser.isPresent()) && ( searchUser.get().getId() != user.getId()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists!", null);

            user.setPassword(encryptPassword(user.getPassword()));

            return Optional.ofNullable(userRepository.save(user));

        }

        return Optional.empty();

    }

    public Optional<UserLogin> authenticateUser(Optional<UserLogin> userLogin) {

        // Gera o Objeto de autenticação
        var credenciais = new UsernamePasswordAuthenticationToken(userLogin.get().getUser(), userLogin.get().getPassword());

        // Autentica o Usuario
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(credenciais);

        // Se a autenticação foi efetuada com sucesso
        if (authentication.isAuthenticated()) {

            // Busca os dados do usuário
            Optional<User> user = userRepository.findByUser(userLogin.get().getUser());

            // Se o usuário foi encontrado
            if (user.isPresent()) {

                // Preenche o Objeto usuarioLogin com os dados encontrados
                userLogin.get().setId(user.get().getId());
                userLogin.get().setName(user.get().getName());
                userLogin.get().setPic(user.get().getPic());
                userLogin.get().setToken(generateToken(userLogin.get().getUser()));
                userLogin.get().setPassword("");

                // Retorna o Objeto preenchido
                return userLogin;

            }

        }

        return Optional.empty();

    }

    private String encryptPassword(String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(password);

    }

    private String generateToken(String user) {
        return "Bearer " + jwtService.generateToken(user);
    }

}

