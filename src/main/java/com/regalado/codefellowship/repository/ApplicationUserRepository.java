package com.regalado.codefellowship.repository;

import com.regalado.codefellowship.model.ApplicationUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository  extends JpaRepository<ApplicationUserModel, Long>
{
    ApplicationUserModel findByUsername(String username);
}