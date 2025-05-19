package com.learning.planr.Services;

import com.learning.planr.Entities.User;
import com.learning.planr.Repositories.UserRepository;
import com.learning.planr.Utilities.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authmanager;

    private BCryptPasswordEncoder encoder;


    public User saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(User user){
        System.out.println("testing");
        Authentication authentication = authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(user.getEmail());
        }
        return "fail";
    }


    public List<User> findAll() {
      return  userRepo.findAll();
    }
}
