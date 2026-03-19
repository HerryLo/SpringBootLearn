package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import com.example.demo.pojo.Employee;
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
                       String gender) {
    	log.info("分页查询，参数：{}, {}, 姓名{}, 性别{}, 开始时间{}， 结束时间{}",page,pageSize, empName, gender, startTime, endTime);
        log.info("参数：姓名{}, 性别{}, 开始时间{}， 结束时间{}", empName, gender, startTime, endTime);
    	PageBean pageBean = employeeService.page(page, pageSize, empName, gender, startTime, endTime);
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
//
//    // 根据部门查询
//    @GetMapping("/dept/{deptId}")
//    public List<Employee> getByDeptId(@PathVariable(required = false) Long deptId) {
//        return employeeService.getByDeptId(deptId);
//    }
//
//    // 模糊搜索
//    @GetMapping("/search")
//    public List<Employee> search(@RequestParam(required = false) String keyword) {
//        return employeeService.search(keyword);
//    }
//
//    // 条件查询
//    @PostMapping("/condition")
//    public List<Employee> getByCondition(@RequestBody(required = false) Employee condition) {
//        return employeeService.getByCondition(condition);
//    }
//
//    // 新增
//    @PostMapping
//    public Object save(@RequestBody(required = false) Employee employee) {
//        boolean success = employeeService.save(employee);
//        if (success) {
//            return employee; // 返回新增后的数据（含ID）
//        }
//        return Collections.singletonMap("success", false);
//    }
//
//    // 修改
//    @PutMapping("/{id}")
//    public Object update(@PathVariable(required = false) Long id,
//                         @RequestBody(required = false) Employee employee) {
//        if (id == null || id <= 0 || employee == null) {
//            return Collections.singletonMap("success", false);
//        }
//        employee.setId(id);
//        boolean success = employeeService.update(employee);
//        return Collections.singletonMap("success", success);
//    }
//
//    // 删除
//    @DeleteMapping("/{id}")
//    public Object delete(@PathVariable(required = false) Long id) {
//        boolean success = employeeService.delete(id);
//        return Collections.singletonMap("success", success);
//    }
//
//    // 批量删除
//    @DeleteMapping("/batch")
//    public Object deleteBatch(@RequestBody(required = false) List<Long> ids) {
//        boolean success = employeeService.deleteBatch(ids);
//        return Collections.singletonMap("success", success);
//    }
}
