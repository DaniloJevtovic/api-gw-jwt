package com.lemur.user.dto;

import com.lemur.user.user.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
