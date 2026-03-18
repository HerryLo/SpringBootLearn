package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.PageBean;

public interface EmployeeService {
	PageBean page(Integer page, Integer pageSize);
}
