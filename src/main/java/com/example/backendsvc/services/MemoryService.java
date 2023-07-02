package com.example.backendsvc.services;

import com.example.backendsvc.data.repositories.MemoryRepository;
import com.example.backendsvc.domain.Memory;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Getter
@Setter
@Builder
public class MemoryService {
    private MemoryRepository memoryRepository;
    public MemoryService(MemoryRepository memoryRespository){
        this.memoryRepository = memoryRespository;
    }
    public void deleteAllMemories(){
        memoryRepository.deleteAll();
    }
    public void deleteAllMemoriesByAuthorId(Long id){
        memoryRepository.deleteAllByAuthorId(id);
    }
    public List<Memory> findAllMemories(){
        return memoryRepository.findAll();
    }
    public List<Memory> findAllMemoriesByAuthorId(Long id){
        return memoryRepository.findAllByAuthorId(id);
    }
    public Optional<Memory> findMemoryById(Long id){
        return memoryRepository.findById(id);
    }
    public Memory saveMemory(Memory memory){
        return memoryRepository.save(memory);
    }
    public void deleteMemoryById(Long id){
        memoryRepository.deleteById(id);
    }
}
