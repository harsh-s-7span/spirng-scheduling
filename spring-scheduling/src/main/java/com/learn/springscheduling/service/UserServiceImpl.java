package com.learn.springscheduling.service;

import com.learn.springscheduling.entity.UserEntity;
import com.learn.springscheduling.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;
    @Override
    public String add(UserEntity userEntity) {
        if(userRepo.save(userEntity) != null)
            return "User saved Successfully";

        return "Something went wrong while saving user.";
    }

    @Override
    public UserEntity getById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepo.findById(id);
        if (userEntityOptional.isPresent())
            return userEntityOptional.get();

        return null;
    }

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> userEntityList = userRepo.findAll();

        if (userEntityList.isEmpty() || userEntityList == null)
            return null;

        return userEntityList;
    }

    @Override
    public UserEntity login(String email) {
        UserEntity userEntity = userRepo.findByEmail(email);

        if (userEntity == null)
            return null;

        LocalDate localDate = LocalDate.now();
        userEntity.setLastLogin(localDate);

        userRepo.save(userEntity);

        return userEntity;
    }


}
