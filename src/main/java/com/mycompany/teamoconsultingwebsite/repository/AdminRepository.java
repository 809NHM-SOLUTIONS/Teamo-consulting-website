package com.mycompany.teamoconsultingwebsite.repository;

import com.mycompany.teamoconsultingwebsite.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String email);

    @Modifying
    @Query("DELETE FROM Admin a WHERE a.email IS NULL OR a.email = ''")
    void deleteInvalidAdmins();
}