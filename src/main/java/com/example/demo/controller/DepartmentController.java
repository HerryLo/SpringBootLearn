package com.example.demo.controller;

import com.example.demo.pojo.Department;
import com.example.demo.pojo.Result;
import com.example.demo.service.Impl.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    public DepartmentServiceImpl DepartmentService;

    public DepartmentController(DepartmentServiceImpl DepartmentService) {
        this.DepartmentService = DepartmentService;
    }

    @GetMapping
    public Result list() {
        List<Department> departments = DepartmentService.list();
        return Result.success(departments);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable(required = false) Integer id) {
        if(id == null) {
            return Result.error("错误");
        }
        Department department = DepartmentService.getById(id);
        return Result.success(department);
    }

    @GetMapping("/search")
    public Result search(@RequestParam(required = false, defaultValue = "") String name) {
        if(name == null) {
            return Result.error("错误");
        }
        return Result.success(DepartmentService.search(name));
    }

    @PostMapping
    public Result save(@RequestBody Department dept) {
        return Result.success(DepartmentService.save(dept));
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Department dept) {
        dept.setId(id);
        return Result.success(DepartmentService.update(dept));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(DepartmentService.delete(id));
    }
}
