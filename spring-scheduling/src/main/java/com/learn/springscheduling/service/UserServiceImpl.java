package com.learn.springscheduling.service;

import com.learn.springscheduling.entity.UserEntity;
import com.learn.springscheduling.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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


    /*
        This method executes at 12:00 AM every day, and will check all user's last login date
        If users are not logged in from last 7 days, it will set those users as "In Active (set InActive = false)"
     */

    @Override
    @Scheduled(cron = "0 0 0 ? * *")
    public void setUsersInactive(){

        System.out.println("******** setUsersInactive() ******* START");

        List<UserEntity> userEntityList = userRepo.findByIsActive(true);

        if(userEntityList != null || !userEntityList.isEmpty()){
            LocalDate currentDate = LocalDate.now();

            for (UserEntity userEntity : userEntityList){
                if(userEntity.getLastLogin().isBefore(currentDate.minusDays(7))){
                    System.out.println("User Name : " + userEntity.getName());
                    System.out.println("Last Login Date : " + userEntity.getLastLogin());
                    userEntity.setIsActive(false);
                    userRepo.save(userEntity);
                }
            }
        }

        System.out.println("******** setUsersInactive() ******* END");
    }
}
