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

    /**
     * 查询部门列表
     * @return
     */
    @GetMapping
    public Result list() {
        List<Department> departments = DepartmentService.list();
        return Result.success(departments);
    }

    /**
     * id获取部门信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable(required = false) Long id) {
        if(id == null) {
            return Result.error("错误");
        }
        Department department = DepartmentService.getById(id);
        return Result.success(department);
    }

    /**
     * name获取部门信息
     * @param name
     * @return
     */
    @GetMapping("/search")
    public Result search(@RequestParam(required = false, defaultValue = "") String name) {
        if(name == null) {
            return Result.error("错误");
        }
        return Result.success(DepartmentService.search(name));
    }

    /**
     * 新增部门信息
     * @param dept
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Department dept) {
        try {
            boolean bool = DepartmentService.save(dept);
            return Result.success(bool);

        }catch (IllegalArgumentException err) {
            return Result.error(String.valueOf(err.getMessage()));
        }
    }

    /**
     * 更新部门信息
     * @param id
     * @param dept
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Department dept) {
        dept.setId(id);
        try {
            boolean bool = DepartmentService.update(dept);
            return Result.success(bool);

        }catch (IllegalArgumentException err) {
            return Result.error(String.valueOf(err.getMessage()));
        }
    }

    /**
     * 删除部门信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        try {
            boolean bool = DepartmentService.delete(id);
            return Result.success(bool);

        }catch (IllegalArgumentException err) {
            return Result.error(String.valueOf(err.getMessage()));
        }
    }
}
