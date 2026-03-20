package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {
    private Long id;

    @NotBlank(message = "员工姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名长度必须在2-20之间")
    private String empName;

    private String password;

    @NotBlank(message = "员工编号不能为空")
    @Pattern(regexp = "^EMP\\d{5}$", message = "编号格式必须为 EMP+5位数字")
    private String empNo;

    @NotNull(message = "性别不能为空")
    @Min(value = 0, message = "性别值错误")
    @Max(value = 2, message = "性别值错误")
    private String gender; // 1男 2女

    @Min(value = 18, message = "年龄必须≥18")
    @Max(value = 65, message = "年龄必须≤65")
    private Integer age;

    @Email(message = "邮箱格式不正确")
    private String email;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @DecimalMin(value = "0.0", inclusive = false, message = "工资必须大于0")
    private String salary;
    private String jobTitle;

    @NotNull(message = "入职日期不能为空")
    private String entryDate;

    private String status; // 1在职 2离职

    // 外键关联
    @NotNull(message = "部门不能为空")
    private String deptId;
    // 非数据库字段：关联部门信息
    private String deptName;
    private String createTime;
    private String updateTime;
}
