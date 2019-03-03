package com.ljy.springboot.docker.managementsystem.mapper;

import com.ljy.springboot.docker.managementsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> getAllUser();

    String getUserPasswd(String phonenumber);

    int installUser(User user);

    int deleteUser(String phonenumber);

}
