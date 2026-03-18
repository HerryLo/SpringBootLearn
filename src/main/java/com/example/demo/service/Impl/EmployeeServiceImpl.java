package com.example.demo.service.Impl;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.pojo.Employee;
import com.example.demo.service.EmployeeService;

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
    public List<Employee> list() {
        List<Employee> list = employeeMapper.selectAll();
        return list != null ? list : Collections.emptyList();
    }
    
    @Override
    public Employee getById(Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return employeeMapper.selectById(id);
    }
    
    @Override
    public List<Employee> getByDeptId(Long deptId) {
        if (deptId == null || deptId <= 0) {
            return Collections.emptyList();
        }
        List<Employee> list = employeeMapper.selectByDeptId(deptId);
        return list != null ? list : Collections.emptyList();
    }
    
    @Override
    public List<Employee> search(String keyword) {
        if (!StringUtils.hasText(keyword)) {
            return list(); // 空关键词返回全部
        }
        String trimKey = keyword.trim();
        if (trimKey.length() > 50) {
            trimKey = trimKey.substring(0, 50);
        }
        List<Employee> list = employeeMapper.search(trimKey);
        return list != null ? list : Collections.emptyList();
    }
    
    @Override
    public List<Employee> getByCondition(Employee condition) {
        if (condition == null) {
            return list();
        }
        List<Employee> list = employeeMapper.selectByCondition(condition);
        return list != null ? list : Collections.emptyList();
    }
    
    @Override
    public boolean save(Employee employee) {
        if (employee == null) {
            return false;
        }
        // 校验必填字段
        if (!StringUtils.hasText(employee.getEmpName())) {
            return false;
        }
        if (!StringUtils.hasText(employee.getEmpNo())) {
            return false;
        }
        // 工号去重检查
        Employee exist = findByEmpNo(employee.getEmpNo());
        if (exist != null) {
            return false; // 工号已存在
        }
        // 默认值
        if (employee.getStatus() == null) {
            employee.setStatus(1);
        }
        if (employee.getGender() == null) {
            employee.setGender(1);
        }
        return employeeMapper.insert(employee) > 0;
    }
    
    @Override
    public boolean update(Employee employee) {
        if (employee == null || employee.getId() == null || employee.getId() <= 0) {
            return false;
        }
        // 检查是否存在
        Employee exist = getById(employee.getId());
        if (exist == null) {
            return false;
        }
        // 如果修改工号，检查是否冲突
        if (StringUtils.hasText(employee.getEmpNo()) && 
            !employee.getEmpNo().equals(exist.getEmpNo())) {
            Employee conflict = findByEmpNo(employee.getEmpNo());
            if (conflict != null) {
                return false;
            }
        }
        return employeeMapper.update(employee) > 0;
    }
    
    @Override
    public boolean delete(Long id) {
        if (id == null || id <= 0) {
            return false;
        }
        // 检查是否存在
        if (getById(id) == null) {
            return false;
        }
        return employeeMapper.deleteById(id) > 0;
    }
    
    @Override
    public boolean deleteBatch(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        // 过滤无效ID
        ids.removeIf(id -> id == null || id <= 0);
        if (ids.isEmpty()) {
            return false;
        }
        return employeeMapper.deleteByIds(ids) > 0;
    }
    
    // 辅助方法：根据工号查询
    private Employee findByEmpNo(String empNo) {
        if (!StringUtils.hasText(empNo)) {
            return null;
        }
        Employee condition = new Employee();
        condition.setEmpNo(empNo);
        List<Employee> list = employeeMapper.selectByCondition(condition);
        return list.isEmpty() ? null : list.get(0);
    }
}
