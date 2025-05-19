package com.learning.planr.Services;

import com.learning.planr.Entities.User;
import com.learning.planr.Repositories.UserRepository;
import com.learning.planr.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;


        this.userRepository.save(User.builder()
                .firstname("zakariae")
                .lastname("akharraz")
                .email("zakaria2003@gmail.com")
                .password(this.encoder.encode("z123456"))
                .role("ADMIN")
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user == null){
            System.out.println("User not found with username: " + username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new CustomUserDetails(userRepository.findByEmail(username));
    }
}
