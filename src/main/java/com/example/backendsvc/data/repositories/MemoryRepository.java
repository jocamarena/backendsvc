package com.example.backendsvc.data.repositories;

import com.example.backendsvc.domain.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
    public List<Memory> findAllByAuthorId(Long id);
}
