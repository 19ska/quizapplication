package com.example.quiz.repositories;

import com.example.quiz.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    //Optional<Admin> findByUserName(String userName);

    Optional<Admin> findByUsername(String username);
}
