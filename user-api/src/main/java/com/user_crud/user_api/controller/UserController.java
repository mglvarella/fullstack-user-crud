package com.user_crud.user_api.controller;
import com.user_crud.user_api.domain.UserRequestDTO;
import com.user_crud.user_api.domain.UserResponseDTO;
import com.user_crud.user_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@Tag(name = "RegisterUser", description = "Controller to maintain user's registry")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Register an user", description = "Method to register a new user")
    @ApiResponse(responseCode = "201", description = "User created")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO data, UriComponentsBuilder uriBuilder) {
        UserResponseDTO createdUser = userService.registerUser(data);

        URI uri = uriBuilder
                .path("/users/{id}")
                .buildAndExpand(createdUser.id())
                .toUri();

        return ResponseEntity.created(uri).body(createdUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an user", description = "Method to delete an user")
    @ApiResponse(responseCode = "204", description = "User deleted")
    @ApiResponse(responseCode = "404", description = "User does not exists")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an user", description = "Method to update an user registry")
    @ApiResponse(responseCode = "200", description = "User updated")
    @ApiResponse(responseCode = "404", description = "User does not exists")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO data){
        return ResponseEntity.ok(userService.updateUser(id, data));
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Method to get all users")
    @ApiResponse(responseCode = "200", description = "All users returned")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an specific user", description = "Method to get an specific user using his Id")
    @ApiResponse(responseCode = "200", description = "User registry found")
    @ApiResponse(responseCode = "404", description = "User does not exists")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }
}
