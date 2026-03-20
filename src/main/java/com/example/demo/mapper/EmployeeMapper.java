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
	public List<Employee> list(String empName, String gender, LocalDateTime startTime, LocalDateTime endTime, String deptId);

	/**
	 * 用户id查询
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(Long id);

	/**
	 * 新增
	 * @param employee
	 * @return
	 */
	int save(Employee employee);

	/**
	 * 修改
	 * @param employee
	 * @return
	 */
	int update(Employee employee);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 查询员工编号
	 * @param empNo
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM employee WHERE emp_no = #{empNo}")
	int countByEmpNo(String empNo);

	/**
	 * 员工登录
	 * @param empNo
	 * @return
	 */
	@Select("SELECT * FROM employee WHERE emp_no = #{empNo} and password = #{password}")
	public Employee getByEmpNoAndPassword(String empNo, String password);
}
