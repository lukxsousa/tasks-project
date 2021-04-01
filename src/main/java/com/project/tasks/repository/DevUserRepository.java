package com.project.tasks.repository;

import com.project.tasks.model.DevUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevUserRepository extends JpaRepository<DevUser, Long> {

    DevUser findByUsername(String username);
}
