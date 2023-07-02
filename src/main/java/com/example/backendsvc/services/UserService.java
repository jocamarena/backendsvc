package com.example.backendsvc.services;

import com.example.backendsvc.data.repositories.UserRepository;
import com.example.backendsvc.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    public User findById(long id){
        return userRepository.findById(id);
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Long save(User user){
        User savedUser = userRepository.save(user);
        if (savedUser.getId() > 1L) {
            return savedUser.getId();
        } else {
            return 0L;
        }
    }
}
