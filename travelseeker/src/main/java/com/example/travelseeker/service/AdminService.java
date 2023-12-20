package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddAdminDTO;
import com.example.travelseeker.model.entities.Admin;
import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.model.enums.UserRoleEnum;
import com.example.travelseeker.repository.AdminRepository;
import com.example.travelseeker.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.userRoleRepository = userRoleRepository;

        this.passwordEncoder = passwordEncoder;
    }

    public Admin getAdminByUsername(String username) {
        return adminRepository.findAdminByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));

    }

    public void setAdminRole(UUID id) {
        UserRole role = this.userRoleRepository.findUserRoleByRole(UserRoleEnum.ADMIN);
        Admin newAdmin = new Admin();
    }

    public void createNewAdmin(AddAdminDTO addAdminDTO) {
        UserRole role = userRoleRepository.findUserRoleByRole(UserRoleEnum.ADMIN);
        Admin newAdmin = new Admin();
        newAdmin.setUsername(addAdminDTO.getUsername())
                .setPassword(passwordEncoder.encode(addAdminDTO.getPassword()))
                .setFirstName("").setLastName("")
                .setEmail(addAdminDTO.getEmail())
                .setCountry("").setAge(0)
                .setRoles(new ArrayList<>());

        newAdmin.getRoles().add(role);

        adminRepository.save(newAdmin);
    }


}
