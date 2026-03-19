package com.example.demo.mapper;

import java.time.LocalDateTime;
import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.pojo.Employee;

@Mapper
public interface EmployeeMapper {

	/**
	 * 分页查询
	 * @return List<Employee>
	 */
	public List<Employee> list(String empName, String gender, LocalDateTime startTime, LocalDateTime endTime);

	public Employee getEmployeeById(Long id);
}
