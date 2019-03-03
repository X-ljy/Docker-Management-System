package com.ljy.springboot.docker.managementsystem.controller;

import com.ljy.springboot.docker.managementsystem.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ImageController {


    @Autowired
    ImageService imageService;

    @PostMapping("/allImageInfo")
    public String allImageInfo(@RequestParam(value = "dockerServerIP") String dockerServerIP) throws IOException {
        return imageService.getAllImageInfo(dockerServerIP);
    }

    @PostMapping("/deleteImage")
    public int deleteImage(@RequestParam(value = "dockerServerIP")String dockerServerIP,
                           @RequestParam(value = "imageID") String imageID){
        return imageService.deleteImage(dockerServerIP, imageID);
    }

    //定义临时dockerfile 存储位置
    String tempFilePath =  "D:\\dockerFiles\\";

    @PostMapping("/buileByDockerfile")
    public int  buileByDockerfile(@RequestParam(value = "dockerServerIP")String dockerServerIP,
                                  @RequestParam(value = "name")String name,
                                  @RequestParam(value = "tag")String tag,
                                  @RequestParam(value = "file")MultipartFile file){

        if(file.getSize() == 0){
                return 0;
        }
        try {
            System.out.println(file.getName() + file.getContentType() + file.getSize() + file.getOriginalFilename());
            File tempFile = new File(tempFilePath + file.getOriginalFilename());
            file.transferTo(tempFile);
            String tarFilePath = tempFile.getPath()+".tar";
            if(imageService.packTarFile(tempFile.getPath(),tarFilePath)){
                return imageService.buileByDockerfile(dockerServerIP,tarFilePath,name,tag);
            }else {
                System.out.println("打包文件失败");
                return 0;
            }

        }catch (IOException e){
            System.out.println("将dockerfile 文件存储失败 ：IOException ");
            e.printStackTrace();
        }
        return 0;
    }

}
