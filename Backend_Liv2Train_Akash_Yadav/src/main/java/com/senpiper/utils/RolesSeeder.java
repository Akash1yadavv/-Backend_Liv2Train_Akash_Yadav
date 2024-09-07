package com.senpiper.utils;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.senpiper.enums.Roles;
import com.senpiper.model.Role;
import com.senpiper.repositories.RoleRepository;

@Component
public class RolesSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired private RoleRepository roleRepository;

    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        Roles[] roleNames = new Roles[] { Roles.USER, Roles.ADMIN };
        
        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);
            
            if (!optionalRole.isPresent()) {
                Role roleToCreate = new Role();
                roleToCreate.setName(roleName);
                
                roleRepository.save(roleToCreate);
            }
        });
    }
}
