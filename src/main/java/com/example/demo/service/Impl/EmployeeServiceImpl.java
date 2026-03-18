package com.example.demo.service.Impl;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.pojo.Employee;
import com.example.demo.pojo.PageBean;
import com.example.demo.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Override
    public PageBean page(Integer page, Integer pageSize) {
    	PageHelper.startPage(page, pageSize);
    	
        List<Employee> list = employeeMapper.list();
        Page<Employee> p = (Page<Employee>) list;
        
        PageBean PageBean = new PageBean(p.getTotal(), p.getResult())
        
        return PageBean;
    }
}
