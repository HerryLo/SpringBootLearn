package com.example.demo.service.Impl;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.pojo.Employee;
import com.example.demo.pojo.PageBean;
import com.example.demo.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String empName, String gender, LocalDateTime startTime, LocalDateTime endTime, String deptId) {
    	PageHelper.startPage(page, pageSize);
    	
        List<Employee> list = employeeMapper.list(empName, gender, startTime, endTime, deptId);
        Page<Employee> p = (Page<Employee>) list;
        
        PageBean PageBean = new PageBean(p.getTotal(), p.getResult());
        
        return PageBean;
    }

    @Override
    public Employee getById(Long id) {
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public boolean save(Employee employee) {
        int count = employeeMapper.countByEmpNo(employee.getEmpNo());
        if (count > 0) {
            throw new IllegalArgumentException("员工编号 " + employee.getEmpNo() + " 已存在");
        }
        return employeeMapper.save(employee) > 0;
    }

    @Override
    public boolean update(Employee employee) {
        return employeeMapper.update(employee) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return employeeMapper.delete(id) > 0;
    }
}
