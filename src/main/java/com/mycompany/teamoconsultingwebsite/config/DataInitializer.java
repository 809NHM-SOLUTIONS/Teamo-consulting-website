package com.mycompany.teamoconsultingwebsite.config;

import com.mycompany.teamoconsultingwebsite.entity.Admin;
import com.mycompany.teamoconsultingwebsite.repository.AdminRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void run(String... args) {

        adminRepository.deleteInvalidAdmins();

        if (adminRepository.findByEmail("admin@teamoconsulting.co.za").isEmpty()) {

            Admin admin = new Admin();

            admin.setFullName("Teamo Admin");
            admin.setEmail("admin@teamoconsulting.co.za");
            admin.setPassword(passwordEncoder.encode("Admin@45"));
            admin.setRole("ADMIN");

            adminRepository.save(admin);
        }
    }
}