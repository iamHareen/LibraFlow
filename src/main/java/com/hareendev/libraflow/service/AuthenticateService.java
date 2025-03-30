package com.hareendev.libraflow.service;

import com.hareendev.libraflow.dto.LoginRequestDTO;
import com.hareendev.libraflow.dto.LoginResponseDTO;
import com.hareendev.libraflow.dto.RegisterRequestDTO;
import com.hareendev.libraflow.model.User;
import com.hareendev.libraflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;


@Service
public class AuthenticateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {

        if(userRepository.findByUserName(registerRequestDTO.getUserName()).isPresent()) {
            throw new RuntimeException("User Already Registered");
        }

        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setUserName(registerRequestDTO.getUserName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }


    public User registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO) {

        if(userRepository.findByUserName(registerRequestDTO.getUserName()).isPresent()) {
            throw new RuntimeException("User Already Registered");
        }

        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setUserName(registerRequestDTO.getUserName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }


    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUserName(),
                        loginRequestDTO.getPassword())
        );

        User user = userRepository.findByUserName(loginRequestDTO.getUserName())
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        String token = jwtService.generateToken();
        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getUserName())
                .roles(user.getRoles())
                .build();

    }


}
