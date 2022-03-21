package com.regalado.codefellowship.repository;

import com.regalado.codefellowship.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository  extends JpaRepository<ApplicationUser, Long>
{
    ApplicationUser findByUsername(String username);
}