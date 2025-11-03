package com.user_crud.user_api.service;

import com.user_crud.user_api.domain.User;
import com.user_crud.user_api.domain.UserRequestDTO;
import com.user_crud.user_api.domain.UserResponseDTO;
import com.user_crud.user_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponseDTO registerUser(UserRequestDTO data){
        User userToRegister = new User(data.name(), data.doc(), data.email(), data.phone());
        User newUser = userRepository.save(userToRegister);

        return new UserResponseDTO(newUser.getId(), newUser.getName(), newUser.getDoc(), newUser.getEmail(), newUser.getPhone());
    }

    public void deleteUserById(Long id){
        User userToDelete = userRepository.findById(id)
                .orElseThrow();
        userRepository.delete(userToDelete);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO data) {
        User user = userRepository.findById(id)
                .orElseThrow();

        user.setName(data.name());
        user.setEmail(data.email());
        user.setPhone(data.phone());

        return new UserResponseDTO(user.getId(), user.getName(), user.getDoc(), user.getEmail(), user.getPhone());
    }


    public List<UserResponseDTO> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getDoc(),
                        user.getEmail(),
                        user.getPhone()
                ))
                .toList();
    }

    public UserResponseDTO findUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow();

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getDoc(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
