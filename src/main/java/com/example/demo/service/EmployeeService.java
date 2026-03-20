package com.example.demo.service;

import java.time.LocalDateTime;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;

public interface EmployeeService {
	PageBean page(Integer page, Integer pageSize, String empName, String gender, LocalDateTime startTime, LocalDateTime endTime, String deptId);

	Employee getById(Long id);

	boolean save(Employee employee);

	boolean update(Employee employee);

	boolean delete(Long id);

	Employee login(Employee employee);
}
