package com.example.demo.mapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.pojo.Employee;

@Mapper
public interface EmployeeMapper {
	// 查询所有（带部门名称）
    List<Employee> selectAll();
    
    // 根据ID查询
    Employee selectById(Long id);
    
    // 根据部门ID查询
    List<Employee> selectByDeptId(Long deptId);
    
    // 模糊查询（姓名、工号、职位）
    List<Employee> search(@Param("keyword") String keyword);
    
    // 条件查询（支持动态SQL）
    List<Employee> selectByCondition(Employee employee);
    
    // 新增
    int insert(Employee employee);
    
    // 修改
    int update(Employee employee);
    
    // 删除
    int deleteById(Long id);
    
    // 批量删除
    int deleteByIds(@Param("ids") List<Long> ids);
    
    // 统计部门人数
    int countByDeptId(Long deptId);
}
