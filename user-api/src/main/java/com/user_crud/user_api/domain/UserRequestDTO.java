package com.user_crud.user_api.domain;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
        @NotBlank(message = "The name can't be blank")
        String name,
        @NotBlank(message = "The document can't be blank")
        String doc,
        @NotBlank(message = "The email can't be blank")
        String email,
        @NotBlank(message = "The phone number can't be blank")
        String phone
) {
}
