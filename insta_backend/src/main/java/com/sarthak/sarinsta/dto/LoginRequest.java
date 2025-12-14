package com.sarthak.sarinsta.dto;

// Accept either username or email from clients
public record LoginRequest(String usernameOrEmail, String password) {
}
