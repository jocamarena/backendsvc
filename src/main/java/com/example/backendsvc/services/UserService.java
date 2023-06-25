package com.example.backendsvc.services;

import com.example.backendsvc.data.repositories.UserRepository;
import com.example.backendsvc.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
    public void deleteAll(){
        userRepository.deleteAll();
    }
}
