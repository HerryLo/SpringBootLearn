package com.example.demo.service;

import com.example.demo.pojo.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> list();
    Department getById(Long id);
    List<Department> search(String name);
    boolean save(Department dept);
    boolean update(Department dept);
    boolean delete(Long id);

}
