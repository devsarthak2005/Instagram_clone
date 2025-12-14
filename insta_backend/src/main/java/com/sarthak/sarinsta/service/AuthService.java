package com.sarthak.sarinsta.service;

import com.sarthak.sarinsta.dto.LoginRequest;
import com.sarthak.sarinsta.dto.SignupRequest;
import com.sarthak.sarinsta.entity.User;
import com.sarthak.sarinsta.repository.UserRepository;
import com.sarthak.sarinsta.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signup(SignupRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    public String login(LoginRequest request) {
        // allow login using either email or username
        String identifier = request.usernameOrEmail();

        User user = userRepository.findByEmail(identifier)
                .or(() -> userRepository.findByUsername(identifier))
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // generate token using user's email as the subject
        return jwtUtil.generateToken(user.getEmail());
    }
}

