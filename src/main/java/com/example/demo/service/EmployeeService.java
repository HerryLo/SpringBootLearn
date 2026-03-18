package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Employee;

public interface EmployeeService {
	List<Employee> list();
    
    Employee getById(Long id);
    
    List<Employee> getByDeptId(Long deptId);
    
    List<Employee> search(String keyword);
    
    List<Employee> getByCondition(Employee condition);
    
    boolean save(Employee employee);
    
    boolean update(Employee employee);
    
    boolean delete(Long id);
    
    boolean deleteBatch(List<Long> ids);
}
