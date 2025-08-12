package org.chen.deptmanagementsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.chen.deptmanagementsystem.anno.Log;
import org.chen.deptmanagementsystem.pojo.Dept;
import org.chen.deptmanagementsystem.pojo.Result;
import org.chen.deptmanagementsystem.service.DeptService;
import org.chen.deptmanagementsystem.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chen
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmpService empService;

    //    @RequestMapping(value = "/depts",method = RequestMethod.GET)//指定请求方式为get
    @GetMapping
    public Result list(){
        List<Dept> deptList = deptService.list();
        log.info("查询全部部门数据");
        return Result.success(deptList);
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询id为{}的部门",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/{id}")
    @Log
    public Result delete(@PathVariable Integer id){
        deptService.delete(id);
        log.info("删除该部门,id为{}",id);
        empService.deleteByDeptId(id);

        log.info("{}部门下的员工也全部删除了",id);
        return Result.success();
    }
    @Log
    @PostMapping()
    public Result add(@RequestBody Dept dept){
        deptService.add(dept);
        log.info("增加了一个{}部门",dept.getName());
        return Result.success();
    }
    @PutMapping()
    @Log
    public Result update(@RequestBody Dept dept){
        log.info("修改了{}部门",dept.getName());
        deptService.update(dept);
        return Result.success();
    }
}
