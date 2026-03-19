package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import com.example.demo.pojo.Employee;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.Impl.EmployeeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/employees", produces = "application/json")
public class EmployeeController {
    
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param empName
     * @param gender
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
                       String empName,
                       String gender,
                       String deptId) {
    	log.info("分页查询，参数：{}, {}, 姓名{}, 性别{}, 开始时间{}， 结束时间{}",page,pageSize, empName, gender, startTime, endTime);
        log.info("参数：姓名{}, 性别{}, 开始时间{}， 结束时间{}", empName, gender, startTime, endTime);
    	PageBean pageBean = employeeService.page(page, pageSize, empName, gender, startTime, endTime, deptId);
    	return Result.success(pageBean);
    }
    

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable(required = false) Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        Employee emp = employeeService.getById(id);
        return Result.success(emp);
    }

    // 新增
    @PostMapping
    public Result save(@Valid @RequestBody Employee employee) {
        try {
            boolean success = employeeService.save(employee);
            return Result.success(success); // 返回新增后的数据（含ID）
        }catch (IllegalArgumentException err){
            return Result.error(err.getMessage());
        }
    }

    // 修改
    @PutMapping("/{id}")
    public Object update(@PathVariable(required = false) Long id,
                         @RequestBody(required = false) Employee employee) {
        if (id == null || id <= 0 || employee == null) {
            return Result.error("id异常或修改对象不能传null");
        }
        employee.setId(id);
        boolean success = employeeService.update(employee);
        return Result.success(success);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(required = false) Long id) {
        boolean success = employeeService.delete(id);
        return Collections.singletonMap("success", success);
    }
//
//    // 批量删除
//    @DeleteMapping("/batch")
//    public Object deleteBatch(@RequestBody(required = false) List<Long> ids) {
//        boolean success = employeeService.deleteBatch(ids);
//        return Collections.singletonMap("success", success);
//    }
}
