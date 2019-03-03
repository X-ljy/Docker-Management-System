package com.ljy.springboot.docker.managementsystem.mapper;

import com.ljy.springboot.docker.managementsystem.entity.ServerIp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ServerIpMapper {

    List<ServerIp> getAllServerIP();

    int deleteServerIP(String ip);

    int insertServerIP(String ip);
}
