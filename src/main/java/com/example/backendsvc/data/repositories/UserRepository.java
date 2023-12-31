package com.example.backendsvc.data.repositories;

import com.example.backendsvc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
    public User findById(long id);
    public User findByEmail(String email);
    public void deleteByUsername(String username);
}
