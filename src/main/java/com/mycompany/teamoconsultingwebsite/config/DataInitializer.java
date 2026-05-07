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
        if (adminRepository.findByEmail("admin@teamoconsulting.co.za").isEmpty()) {
            Admin admin = new Admin(
                    "Teamo Admin",
                    "" +
                            "",
                    passwordEncoder.encode("Admin@45"),
                    "ADMIN"
            );

            adminRepository.save(admin);
        }
    }
}