package org.chen.deptmanagementsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.chen.deptmanagementsystem.pojo.Result;
import org.chen.deptmanagementsystem.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Chen
 * @version 1.0
 */
@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    //本地存储方式
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile image) throws IOException {
//        log.info("成功");
//        String originalFilename = image.getOriginalFilename();
//        originalFilename = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() + "." + originalFilename;
//        image.transferTo(new File("D://"+newFileName));
//        return Result.success();
//    }
    //云端存储方式
    @PostMapping("/upload")
    public Result upload( MultipartFile image) throws IOException {
        log.info("文件开始上传"+image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件上传成功"+url);
        return Result.success(url);
    }
}
