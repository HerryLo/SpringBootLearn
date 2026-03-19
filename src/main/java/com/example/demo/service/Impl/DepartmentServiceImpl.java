package com.example.demo.service.Impl;

import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.pojo.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> list() {
        return departmentMapper.selectAll();
    }

    @Override
    public Department getById(Long id) {
        // 校验ID
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("部门ID不能为空或小于等于0");
        }
        return departmentMapper.selectById(id);
    }

    @Override
    public List<Department> search(String name) {
        if (!StringUtils.hasText(name)) {
            return Collections.emptyList();
        }
        // 去除前后空格，防止SQL注入风险
        String trimName = name.trim();
        if (trimName.length() > 50) {
            throw new IllegalArgumentException("搜索名称长度不能超过50");
        }
        return departmentMapper.selectByName(name);
    }

    @Override
    public boolean save(Department dept) {
        if (dept == null) {
            throw new IllegalArgumentException("部门信息不能为空");
        }
        // 校验名称
        if (!StringUtils.hasText(dept.getDeptName())) {
            throw new IllegalArgumentException("部门名称不能为空");
        }
        String trimName = dept.getDeptName().trim();
        if (trimName.length() > 50) {
            throw new IllegalArgumentException("部门名称长度不能超过50");
        }
        dept.setDeptName(trimName);

        // 检查是否已存在
        List<Department> exist = departmentMapper.selectByNameExact(trimName);
        if (!exist.isEmpty()) {
            throw new IllegalArgumentException("部门名称已存在");
        }

        return departmentMapper.insert(dept) > 0;
    }

    @Override
    public boolean update(Department dept) {
        // 校验对象
        if (dept == null) {
            throw new IllegalArgumentException("部门信息不能为空");
        }
        // 校验ID
        if (dept.getId() == null || dept.getId() <= 0) {
            throw new IllegalArgumentException("部门ID不能为空或小于等于0");
        }
        // 校验名称（如果传了名称）
        if (StringUtils.hasText(dept.getDeptName())) {
            String trimName = dept.getDeptName().trim();
            if (trimName.length() > 50) {
                throw new IllegalArgumentException("部门名称长度不能超过50");
            }
            dept.setDeptName(trimName);

            // 检查名称是否被其他部门使用
            Department exist = departmentMapper.selectById(dept.getId());
            if (exist != null && !trimName.equals(exist.getDeptName())) {
                List<Department> sameName = departmentMapper.selectByNameExact(trimName);
                if (!sameName.isEmpty()) {
                    throw new IllegalArgumentException("部门名称已被其他部门使用");
                }
            }
        }

        return departmentMapper.update(dept) > 0;
    }

    @Override
    public boolean delete(Long id) {
        // 校验ID
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("部门ID不能为空或小于等于0");
        }
        // 检查是否存在
        Department exist = departmentMapper.selectById(id);
        if (exist == null) {
            throw new IllegalArgumentException("部门不存在");
        }
        // 检查是否有员工（假设有员工表关联）
        int empCount = departmentMapper.countEmployeeByDeptId(id);
        if (empCount > 0) {
            throw new IllegalArgumentException("该部门下存在员工，无法删除");
        }

        return departmentMapper.deleteById(id) > 0;
    }
}
