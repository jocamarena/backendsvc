package com.example.backendsvc.api;

import com.example.backendsvc.data.dto.UserDTO;
import com.example.backendsvc.domain.User;
import com.example.backendsvc.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return convertUserListToUserDTOList(userService.findAll());
    }
    public List<UserDTO> convertUserListToUserDTOList(List<User> users){
        List<UserDTO> userDTOS = users.stream().map(user -> {
            return UserDTO.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .enabled(user.isEnabled())
                    .accountNonExpired(user.isAccountNonExpired())
                    .credentialsNonExpired(user.isCredentialsNonExpired())
                    .accountNonLocked(user.isAccountNonLocked())
                    .password("**********")
                    .build();
        }).collect(Collectors.toList());
        return userDTOS;
    }
}
