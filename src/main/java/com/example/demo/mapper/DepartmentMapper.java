package com.example.demo.mapper;

import com.example.demo.pojo.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    // 查询所有
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "dept_name",property = "deptName"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })
    @Select("SELECT * FROM department ORDER BY id")
    List<Department> selectAll();

    // 根据ID查询
    Department selectById(Integer id);

    // 模糊查询
    List<Department> selectByName(@Param("name") String name);

    // 新增
    int insert(Department dept);

    // 修改
    int update(Department dept);

    // 删除
    int deleteById(Integer id);

    // 精确查询名称（用于判重）
    @Select("SELECT * FROM department WHERE dept_name = #{name}")
    List<Department> selectByNameExact(String name);

    // 统计部门下员工数量
    @Select("SELECT COUNT(*) FROM employee WHERE dept_id = #{deptId}")
    int countEmployeeByDeptId(Integer deptId);
}
