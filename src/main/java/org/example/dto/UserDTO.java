package org.example.dto;

public record UserDTO(
        int id,
        String username,
        String password,
        String role) {
}