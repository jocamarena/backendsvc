package com.example.backendsvc.api;

import com.example.backendsvc.data.dto.UserDTO;
import com.example.backendsvc.domain.User;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return convertUserListToUserDTOList(userService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()){
            return ResponseEntity.ok(getUserDTO(optionalUser.get()));
        } else return ResponseEntity.notFound().build();
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
        logger.info("username: " + username);
        User user = userService.findByUsername(username);
        logger.info("user: " + user);
        if (user != null){
            logger.info("if:userDTO: " + getUserDTO(user));
            return ResponseEntity.ok(getUserDTO(user));
        } else {
            logger.info("else:ResponseEntity.notFound().build()");
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
        User user = userService.findByEmail(email);
        if (user != null){
            return ResponseEntity.ok(getUserDTO(user));
        } else return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id){
        Optional<User> optionalUser = userService.findById(id);
         if (optionalUser.isPresent()){
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/username/{username}")
    public ResponseEntity deleteUserByUsername(@PathVariable String username){
        User user = userService.findByUsername(username);
        if (user != null){
            userService.deleteByUsername(username);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<String> saveNewUser(@RequestBody UserDTO userDTO){
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .enabled(userDTO.isEnabled())
                .accountNonExpired(userDTO.isAccountNonExpired())
                .credentialsNonExpired(userDTO.isCredentialsNonExpired())
                .accountNonLocked(userDTO.isAccountNonLocked())
                .createdDate(DateTimeUtils.getCurrentTimeStamp())
                .build();

        if (findUserByUsername(userDTO.getUsername())){
            String userFoundMessage = "User with email " + userDTO.getEmail() +  " already exists";
            logger.info(userFoundMessage);
            return ResponseEntity.badRequest().body(userFoundMessage);
        } else {
            String userMessage = saveUser(user);
            if (!userMessage.startsWith("Exception") && Long.parseLong(userMessage) > 0L) {
                URI userUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
                return ResponseEntity.ok("User saved successfully" + userUri);
            } else {
                logger.info("Exception thrown while saving user");
                return ResponseEntity.badRequest().body(userMessage);
            }
        }
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
                    .createdDate(user.getCreatedDate())
                    .password("**********")
                    .build();
        }).collect(Collectors.toList());
        return userDTOS;
    }
    public UserDTO getUserDTO(User user){
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .enabled(user.isEnabled())
                .accountNonExpired(user.isAccountNonExpired())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .createdDate(user.getCreatedDate())
                .password("**********")
                .build();
    }
    public Long getLastUserId(){
        List<User> users = userService.findAll();
        if (users.size() > 0) {
            return users.get(users.size() - 1).getId();
        } else {
            return 0L;
        }
    }
    public String saveUser(User user){
        try{
            return userService.save(user).toString();
        } catch (Exception e) {
            return "Exception saving user: " + e.getMessage();
        }
    }
    public boolean findUserByUsername(String username){
        User user = userService.findByUsername(username);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
}
