package com.learn.springscheduling.controller;

import com.learn.springscheduling.entity.UserEntity;
import com.learn.springscheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String add(@RequestBody UserEntity userEntity){
        return userService.add(userEntity);
    }

    @GetMapping("/{id}")
    public UserEntity getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @GetMapping
    public List<UserEntity> getAll(){
        return userService.getAll();
    }

    @GetMapping("/login/{email}")
    public UserEntity login(@PathVariable String email){
        return userService.login(email);
    }
}
