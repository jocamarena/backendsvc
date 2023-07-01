package com.example.backendsvc.api;

import com.example.backendsvc.domain.Memory;
import com.example.backendsvc.services.MemoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{id}/memories")
public class MemoryController {
    private MemoryService memoryService;
    public MemoryController(MemoryService memoryService){
        this.memoryService = memoryService;
    }
    @DeleteMapping
    public void deleteAllMemories(){
        memoryService.deleteAllMemories();
    }
    public List<Memory> findAllMemories(){
        return memoryService.findAllMemories();
    }
    public Memory saveMemory(Memory memory){
        return memoryService.saveMemory(memory);
    }
}
