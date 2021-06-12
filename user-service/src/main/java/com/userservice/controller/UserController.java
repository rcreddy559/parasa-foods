package com.userservice.controller;

import com.userservice.exception.URLExceptionHandler;
import com.userservice.exception.UserNotFoundException;
import com.userservice.model.UserModel;
import com.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/user")
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
    public UserModel getUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel userModel) {
        return userService.saveUser(userModel);
    }

    @PutMapping("/{id}")
    public UserModel saveOrUpdateUser(@PathVariable Long id, @RequestBody UserModel user) {
        return userService.saveOrUpdateUser(id, user);
    }

    @PatchMapping("/{id}")
    public UserModel patchUser(@PathVariable Long id,
                          @RequestBody Map<String, Object> patchObjects) throws UserNotFoundException {
        return userService.patchUser(id, patchObjects);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = {"*"}, method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST})
    public void noMatchFound() throws URLExceptionHandler {
        log.error("URLExceptionHandler ");
        throw new URLExceptionHandler("URL Not match");
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        return ResponseEntity.ok().allow(HttpMethod.DELETE,
                HttpMethod.GET,
                HttpMethod.PATCH,
                HttpMethod.POST,
                HttpMethod.POST,
                HttpMethod.PUT).build();
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
