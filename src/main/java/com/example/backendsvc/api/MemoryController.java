package com.example.backendsvc.api;

import com.example.backendsvc.data.dto.MemoryDTO;
import com.example.backendsvc.domain.Memory;
import com.example.backendsvc.services.MemoryService;
import com.example.backendsvc.services.UserService;
import com.example.backendsvc.utils.DateTimeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @GetMapping
    public ResponseEntity<MemoryDTO> findDefaultMemory(@PathVariable Long id){
        Memory memory = createDefaultMemory(id);
        return ResponseEntity.ok(MemoryDTO.builder()
                .title(memory.getTitle())
                .content(memory.getContent())
                .createdDate(memory.getCreatedDate())
                .modifiedDate(memory.getModifiedDate())
                .authorId(memory.getAuthor().getId()).build());
    }
    public ResponseEntity<List<Memory>> findAllMemories(){
        return ResponseEntity.ok(memoryService.findAllMemories());
    }
    @PostMapping
    public ResponseEntity<Memory> saveMemory(@RequestBody MemoryDTO memoryDTO, @PathVariable Long id){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(memoryService.saveMemory(Memory.builder()
                        .title(memoryDTO.getTitle())
                        .content(memoryDTO.getContent())
                        .createdDate(DateTimeUtils.getCurrentTimeStamp())
                        .modifiedDate(DateTimeUtils.getCurrentTimeStamp())
                        .author(userService.findById(id)).build()).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    public Memory createDefaultMemory(Long id){
        return Memory.builder()
                .author(userService.findById(id))
                .title("Default memory")
                .content("My memory details....")
                .createdDate(DateTimeUtils.getCurrentTimeStamp()).build();
    }
}
