package org.chen.deptmanagementsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.chen.deptmanagementsystem.anno.Log;
import org.chen.deptmanagementsystem.pojo.Emp;
import org.chen.deptmanagementsystem.pojo.PageBean;
import org.chen.deptmanagementsystem.pojo.Result;
import org.chen.deptmanagementsystem.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Chen
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询page:{},pageSize:{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }
    @DeleteMapping("/{ids}")
    @Log
    public Result delete(@PathVariable List<Integer> ids) {//要用foreach遍历,用集合包装
        log.info("删除以下id员工:{}",ids);
        empService.delete(ids);
        return Result.success();
    }
    @PostMapping
    @Log
    public Result save(@RequestBody Emp emp) {
        log.info("创建了新的员工emp{}",emp);
        empService.save(emp);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("查询该id的员工:{}",id);
        Emp emp = empService.selectById(id);
        return Result.success(emp);
    }
    @PutMapping
    @Log
    public Result update(@RequestBody Emp emp) {
        log.info("修改了员工{}",emp);
        empService.update(emp);
        return Result.success();
    }

}
