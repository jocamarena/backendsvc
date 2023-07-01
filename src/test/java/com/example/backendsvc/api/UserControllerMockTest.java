package com.example.backendsvc.api;

import com.example.backendsvc.data.dto.UserDTO;
import com.example.backendsvc.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserController userController;
    private final String BASE_URL = "http://localhost:8080";
    private List<User> userList = new ArrayList<>();
    private List<UserDTO> userDTOS = new ArrayList<>();
    @BeforeEach
    public void setUp() {
        userList.add(User.builder()
                .email("jose.j.camarena@gmail.com")
                .username("jcamarena")
                .password("123456").build());
        userDTOS.add(UserDTO.builder()
                .username("jcamarena")
                .email("jose.j.camarena@gmail.com")
                .password("123456").build());
    }
    @Test
    public void getAllUsersMockTest() throws Exception {
        mockMvc.perform(get(BASE_URL + "/api/v1/users"))
                .andExpect(status().isOk());
    }
    @Test
    public void getAllUsersControllerTest(){
        when(userController.getAllUsers()).thenReturn(userDTOS);
        assertTrue(userController.getAllUsers().size() > 0);
    }
}
