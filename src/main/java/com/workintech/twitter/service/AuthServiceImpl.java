package com.workintech.twitter.service;

import com.workintech.twitter.entity.Role;
import com.workintech.twitter.entity.UserSecurity;
import com.workintech.twitter.exceptions.UserAlreadyRegisteredException;
import com.workintech.twitter.repository.RoleRepository;
import com.workintech.twitter.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{
    private UserSecurityRepository userSecurityRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserSecurityRepository userSecurityRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userSecurityRepository = userSecurityRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserSecurity register(String email, String password) {
        Optional<UserSecurity> optionalUser = userSecurityRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            throw new UserAlreadyRegisteredException("Email already Registered");
        }

        String encodePassword = passwordEncoder.encode(password);

        Optional<Role> userRole = roleRepository.findByAuthority("USER");

        if(userRole.isEmpty()){
            Role role = new Role();
            role.setAuthority("USER");

            userRole = Optional.of(roleRepository.save(role));
        }

        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setEmail(email);
        userSecurity.setPassword(encodePassword);
        userSecurity.setAuthorities(Set.of(userRole.get()));



        return userSecurityRepository.save(userSecurity);
    }
}
