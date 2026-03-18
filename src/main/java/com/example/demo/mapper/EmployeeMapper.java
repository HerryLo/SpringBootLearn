package com.example.demo.mapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.pojo.Employee;

@Mapper
public interface EmployeeMapper {	
	@Select("select * from employee")
	public List<Employee> list();
}
