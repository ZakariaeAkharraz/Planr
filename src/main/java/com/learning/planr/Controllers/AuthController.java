package com.learning.planr.Controllers;

import com.learning.planr.Entities.User;
import com.learning.planr.Services.UserService;
import com.learning.planr.Utilities.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        service.saveUser(user);
        return "done";
    }
    @PostMapping("/login")
    public String Login(@RequestBody User user){
        return service.verify(user);
    }
}
