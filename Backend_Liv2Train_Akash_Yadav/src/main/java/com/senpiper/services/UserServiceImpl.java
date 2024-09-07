package com.senpiper.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.senpiper.dtos.LoginUserDto;
import com.senpiper.dtos.UserDto;
import com.senpiper.enums.Roles;
import com.senpiper.exceptions.UserException;
import com.senpiper.model.Role;
import com.senpiper.model.User;
import com.senpiper.repositories.RoleRepository;
import com.senpiper.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private PasswordEncoder passwordEncoder;
    
    @Autowired private AuthenticationManager authenticationManager;
    
    @Autowired private UserRepository userRepository;
    
    @Autowired private RoleRepository roleRepository;

    @Override
    public User registerUser(UserDto userdto) throws UserException {
        if (userdto == null) {
            throw new UserException("user details cannot be null.");
        }
        Optional<User> existingUser = userRepository.findByEmail(userdto.getEmail());
        if(existingUser.isPresent()) {
        	throw new UserException("User allready exist for this email:- "+userdto.getEmail());
        }
        Optional<Role> optionalRole = roleRepository.findByName(Roles.ADMIN);
        if (optionalRole.isEmpty()) {
            throw new UserException("Role 'ADMIN' not found.");
        }
        User user = new User();
        user.setRole(optionalRole.get());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setEmail(userdto.getEmail());
        user.setName(userdto.getName());
        user.setPhone(userdto.getPhone());

        return userRepository.save(user);
    }

    @Override
    public User loginUser(LoginUserDto loginDetails) throws UserException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDetails.getEmail(), loginDetails.getPassword())
        );

        return userRepository.findByEmail(loginDetails.getEmail())
                .orElseThrow(() -> new UserException("Customer not found for email: " + loginDetails.getEmail()));
    }


}
