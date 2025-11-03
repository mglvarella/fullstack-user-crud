package com.user_crud.user_api.domain;

public record UserResponseDTO(
        Long id,
        String name,
        String doc,
        String email,
        String phone
) {
}
