package com.user_crud.user_api.service;

import com.user_crud.user_api.domain.User;
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

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        User userToDelete = userRepository.findById(id)
                .orElseThrow();
        userRepository.delete(userToDelete);
    }

    @Transactional
    public User updateUser(Long id, User data){
        User userToUpdate = userRepository.findById(id)
                .orElseThrow();

        if(!userToUpdate.getName().equals(data.getName())){
            userToUpdate.setName(data.getName());
        }
        if(!userToUpdate.getEmail().equals(data.getEmail())){
            userToUpdate.setEmail(data.getEmail());
        }
        if(!userToUpdate.getPhone().equals(data.getPhone())){
            userToUpdate.setPhone(data.getPhone());
        }

        return userToUpdate;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow();
    }
}
