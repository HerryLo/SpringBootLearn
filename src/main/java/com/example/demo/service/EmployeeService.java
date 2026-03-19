package com.example.demo.service;

import java.time.LocalDateTime;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.PageBean;

public interface EmployeeService {
	PageBean page(Integer page, Integer pageSize, String empName, String gender, LocalDateTime startTime, LocalDateTime endTime);

	Employee getById(Long id);
}
