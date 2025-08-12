package org.chen.deptmanagementsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.chen.deptmanagementsystem.pojo.Emp;
import org.chen.deptmanagementsystem.pojo.Result;
import org.chen.deptmanagementsystem.service.EmpService;
import org.chen.deptmanagementsystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chen
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    EmpService empService;
    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("用户名:{}",emp.getUsername());
        log.info("密码:{}",emp.getPassword());
        Emp e = empService.login(emp);
        if(e!=null){
            Map<String, Object> claims  = new HashMap<>();
            claims.put("username",e.getUsername());
            claims.put("password",e.getPassword());
            claims.put("id",e.getId());
            String s = JwtUtils.generateJwt(claims);
            return Result.success(s);
        }
        return Result.error("用户名或密码错误");

    }
}
