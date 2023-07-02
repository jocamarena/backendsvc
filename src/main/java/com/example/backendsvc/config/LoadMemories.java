package com.example.backendsvc.config;

import com.example.backendsvc.domain.Memory;
import com.example.backendsvc.domain.User;
import com.example.backendsvc.services.MemoryService;
import com.example.backendsvc.services.UserService;
import com.example.backendsvc.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoadMemories implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(LoadMemories.class);
    private MemoryService memoryService;
    private UserService userService;
    public LoadMemories(MemoryService memoryService, UserService userService){
        this.memoryService = memoryService;
        this.userService = userService;
    }
    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading memories...");
    }
}
