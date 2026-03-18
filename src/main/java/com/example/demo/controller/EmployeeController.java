package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.pojo.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.Impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    private EmployeeService employeeService;

    public void DepartmentController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    
    // 查询所有
    @GetMapping
    public List<Employee> list() {
        return employeeService.list();
    }
    
    // 根据ID查询
    @GetMapping("/{id}")
    public Employee getById(@PathVariable(required = false) Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return employeeService.getById(id);
    }
    
    // 根据部门查询
    @GetMapping("/dept/{deptId}")
    public List<Employee> getByDeptId(@PathVariable(required = false) Long deptId) {
        return employeeService.getByDeptId(deptId);
    }
    
    // 模糊搜索
    @GetMapping("/search")
    public List<Employee> search(@RequestParam(required = false) String keyword) {
        return employeeService.search(keyword);
    }
    
    // 条件查询
    @PostMapping("/condition")
    public List<Employee> getByCondition(@RequestBody(required = false) Employee condition) {
        return employeeService.getByCondition(condition);
    }
    
    // 新增
    @PostMapping
    public Object save(@RequestBody(required = false) Employee employee) {
        boolean success = employeeService.save(employee);
        if (success) {
            return employee; // 返回新增后的数据（含ID）
        }
        return Collections.singletonMap("success", false);
    }
    
    // 修改
    @PutMapping("/{id}")
    public Object update(@PathVariable(required = false) Long id, 
                         @RequestBody(required = false) Employee employee) {
        if (id == null || id <= 0 || employee == null) {
            return Collections.singletonMap("success", false);
        }
        employee.setId(id);
        boolean success = employeeService.update(employee);
        return Collections.singletonMap("success", success);
    }
    
    // 删除
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(required = false) Long id) {
        boolean success = employeeService.delete(id);
        return Collections.singletonMap("success", success);
    }
    
    // 批量删除
    @DeleteMapping("/batch")
    public Object deleteBatch(@RequestBody(required = false) List<Long> ids) {
        boolean success = employeeService.deleteBatch(ids);
        return Collections.singletonMap("success", success);
    }
}
