package com.erp.service;

import com.erp.dtos.RegisterUserDto;
import com.erp.repository.RoleRepository;
import com.erp.model.Role;
import com.erp.model.RoleEnum;
import com.erp.model.User;
import com.erp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    public User createAdministrator(RegisterUserDto registerUserDto) {
        Optional<Role> adminRole = roleRepository.findByName(RoleEnum.ADMIN);
        if (adminRole.isEmpty()) {
            throw new RuntimeException("Admin role not found");
        }

        User admin = new User();
        admin.setFullName(registerUserDto.getFullName());
        admin.setEmail(registerUserDto.getEmail());
        admin.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        admin.setRole(adminRole.get());

        return userRepository.save(admin);
    }


}
