package com.learning.planr.Controllers;


import com.learning.planr.Entities.User;
import com.learning.planr.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public List<User> getAll(){
        return service.findAll();
    }
//    @PostMapping("/add")
//    public User register(@RequestBody User user){
//
//        return service.saveUser(user);
//    }
    @PostMapping("/test")
    public String testing(){
        return "Hello Worlddddd";
    }


}
