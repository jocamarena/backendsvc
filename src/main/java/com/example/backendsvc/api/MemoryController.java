package com.example.backendsvc.api;

import com.example.backendsvc.data.dto.MemoryDTO;
import com.example.backendsvc.domain.Memory;
import com.example.backendsvc.domain.User;
import com.example.backendsvc.services.MemoryService;
import com.example.backendsvc.services.UserService;
import com.example.backendsvc.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users/{id}/memories")
public class MemoryController {
    private Logger logger = LoggerFactory.getLogger(MemoryController.class);
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
    @GetMapping("/default")
    public ResponseEntity<Memory> findDefaultMemory(@PathVariable Long id){
        Memory memory = createDefaultMemory(id);
        return ResponseEntity.ok(memory);
    }
    @GetMapping
    public ResponseEntity<List<Memory>> findAllMemories(@PathVariable Long id){
        return ResponseEntity.ok(memoryService.findAllMemoriesByAuthorId(id));
    }
    @GetMapping("/{memoryId}")
    public ResponseEntity<Optional<Memory>>  findMemoryById(@PathVariable Long memoryId){
        return ResponseEntity.ok(memoryService.findMemoryById(memoryId));
    }
    @PostMapping
    public ResponseEntity<Memory> saveMemory(@RequestBody MemoryDTO memoryDTO, @PathVariable Long id) throws Exception {
        User user = userService.findById(id).orElseThrow(() -> new Exception("User not found"));
        Memory memory = Memory.builder()
                .title(memoryDTO.getTitle())
                .content(memoryDTO.getContent())
                .createdDate(DateTimeUtils.getCurrentTimeStamp())
                .author(user)
                .build();
        memory = memoryService.saveMemory(memory);
/*        List<Memory> memories = user.getMemories();
        memories.add(memory);
        user.setMemories(memories);*/
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")

                .buildAndExpand(memory).toUri();
/*        logger.info("user.getMemories(): " + user.getMemories().size());
        logger.info("URI" + uri);*/
        return ResponseEntity.created(uri).build();
    }
    public Memory createDefaultMemory(Long id){
        return Memory.builder()
                .title("Default memory")
                .content("My memory details....")
                .createdDate(DateTimeUtils.getCurrentTimeStamp()).build();
    }
}
