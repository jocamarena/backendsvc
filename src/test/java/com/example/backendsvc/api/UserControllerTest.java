package com.example.backendsvc.api;

import com.example.backendsvc.data.dto.UserDTO;
import com.example.backendsvc.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserControllerTest {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserController userController;
    @Test
    void getAllUsers() {
        int size = userController.getAllUsers().size();
        assert size > 0;
        assertTrue(size > 0);
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByUsername() {
        userController.getAllUsers().stream()
                        .forEach(userDTO -> logger.info("email: " + userDTO.getEmail()));
        ResponseEntity< UserDTO> userDTO = userController.getUserByEmail("jose.j.camarena@gmail.com");
        logger.info("userDTO: " + userDTO.getBody().getEmail());
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void saveNewUser() {
    }

    @Test
    void convertUserListToUserDTOList() {
    }

    @Test
    void getUserDTO() {
    }

    @Test
    void getLastUserId() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void findUserByUsername() {
    }
}