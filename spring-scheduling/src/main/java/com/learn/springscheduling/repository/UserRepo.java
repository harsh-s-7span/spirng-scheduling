package com.learn.springscheduling.repository;

import com.learn.springscheduling.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    public UserEntity findByEmail(String email);

    public List<UserEntity> findByIsActive(Boolean isActive);
}
