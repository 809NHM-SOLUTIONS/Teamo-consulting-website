package com.mycompany.teamoconsultingwebsite.config;

import com.mycompany.teamoconsultingwebsite.entity.Admin;
import com.mycompany.teamoconsultingwebsite.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        Admin admin = adminRepository.findByEmail("admin@teamoconsulting.co.za")
                .orElse(new Admin());

        admin.setFullName("Teamo Admin");
        admin.setEmail("admin@teamoconsulting.co.za");
        admin.setPassword(passwordEncoder.encode("Admin@45"));
        admin.setRole("ADMIN");

        adminRepository.save(admin);
    }
}