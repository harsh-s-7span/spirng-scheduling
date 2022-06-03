package com.learn.springscheduling.repository;

import com.learn.springscheduling.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
}
