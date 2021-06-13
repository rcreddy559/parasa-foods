package com.user.controller;

import com.user.model.UserModel;
import com.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.findAllUsers();
    }


    ///{23}/userdetails
    @GetMapping("/{id}")
    public UserModel getUser(@PathVariable Long id) throws Exception {
        return userService.findById(id);
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel userModel) {
        return userService.saveUser(userModel);
    }

    @PutMapping("/{id}")
    public UserModel saveOrUpdateUser(@PathVariable Long id, @RequestBody UserModel user) throws Exception {
        return userService.saveOrUpdateUser(id, user);
    }

    @PatchMapping("/{id}")
    public UserModel patchUser(@PathVariable Long id,
                               @RequestBody Map<String, Object> patchObjects) throws Exception {
        return userService.patchUser(id, patchObjects);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/calculations")
    public Map<String, Object> calculations() {
        return userService.calculations();
    }

    @GetMapping("/read-headers")
    public Map<String, Object> readHeaders(@RequestHeader Map<String, Object> headers) {
        headers.forEach((key, value)-> log.info("Key: {}, Value: {}", key, value));
        return headers;
    }
}
