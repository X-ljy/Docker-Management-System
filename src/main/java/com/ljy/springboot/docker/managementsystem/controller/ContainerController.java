package com.ljy.springboot.docker.managementsystem.controller;

import com.ljy.springboot.docker.managementsystem.services.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ContainerController {

    @Autowired
    ContainerService containerService;

    @PostMapping("/containersInfo")
    public String getContainersInfo(@RequestParam(value = "dockerServerIP") String dockerServerIP)
            throws IOException {
        return containerService.queryContainersInfo(dockerServerIP);
    }

    @PostMapping("/createContainer")
    public int createContainer(@RequestParam(value = "dockerServerIP") String dockerServerIP,
                               @RequestParam(value = "imageID") String imageID,
                               @RequestParam(value = "containerName")String containerName
                               ){
        System.out.println(dockerServerIP+imageID+containerName);
        return containerService.createContainer(dockerServerIP, containerName, imageID);
    }

    @PostMapping("/startContainer")
    public int startContainer(@RequestParam(value = "dockerServerIP")String dockerServerIP,
                              @RequestParam(value = "containerID")String containerID){
        return containerService.startContainer(dockerServerIP, containerID);
    }

    @PostMapping("/restartContainer")
    public int restartContainer(@RequestParam(value = "dockerServerIP")String dockerServerIP,
                                @RequestParam(value = "containerID")String containerID){

        return containerService.restartContainer(dockerServerIP, containerID);
    }

    @PostMapping("/killContainer")
    public int killContainer(@RequestParam(value = "dockerServerIP")String dockerServerIP,
                             @RequestParam(value = "containerID")String containerID){
        return containerService.killContainer(dockerServerIP, containerID);
    }

    @PostMapping("/removeContainer")
    public int removeContainer(@RequestParam(value = "dockerServerIP") String dockerServerIP ,
                               @RequestParam(value = "containerID") String containerID){
        return containerService.removeContainer(dockerServerIP, containerID);
    }

}
