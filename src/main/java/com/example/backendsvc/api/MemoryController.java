package com.example.backendsvc.api;

import com.example.backendsvc.domain.Memory;
import com.example.backendsvc.services.MemoryService;
import com.example.backendsvc.services.UserService;
import com.example.backendsvc.utils.DateTimeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{id}/memories")
public class MemoryController {
    private MemoryService memoryService;
    private UserService userService;
    public MemoryController(MemoryService memoryService, UserService userService){
        this.memoryService = memoryService;
        this.userService = userService;
    }
    @DeleteMapping
    public void deleteAllMemories(){
        memoryService.deleteAllMemories();
    }
    @GetMapping()
    public Memory findDefaultMemory(@PathVariable Long id){
        return Memory.builder()
                .author(userService.findById(id))
                .title("Default Memory")
                .content("My memory details....")
                .createdDate(DateTimeUtils.getCurrentTimeStamp())
                .build();
    }
    public List<Memory> findAllMemories(){
        return memoryService.findAllMemories();
    }
    public Memory saveMemory(Memory memory){
        return memoryService.saveMemory(memory);
    }
}
