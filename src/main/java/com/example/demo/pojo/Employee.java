package com.example.demo.pojo;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String empName;
    private String empNo;
    private String gender;
    private Integer age;
    private String email;
    private String phone;
    private String salary;
    private String jobTitle;
    private String entryDate;
    private String status;
    // 外键关联
    private String deptId;
    // 非数据库字段：关联部门信息
    private String deptName;
    private String createTime;
    private String updateTime;
}
