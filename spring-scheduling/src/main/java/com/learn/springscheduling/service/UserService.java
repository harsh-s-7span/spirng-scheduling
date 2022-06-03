package com.learn.springscheduling.service;

import com.learn.springscheduling.entity.UserEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public String add(UserEntity userEntity);

    public UserEntity getById(Long id);

    public List<UserEntity> getAll();

    public UserEntity login(String email);

    public void setUsersInactive();
}
