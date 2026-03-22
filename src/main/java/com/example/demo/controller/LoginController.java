package com.example.demo.controller;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.Result;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.Impl.EmployeeServiceImpl;
import com.example.demo.utils.JwtsUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    EmployeeService employeeService;

    public LoginController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody Employee employee){
        Employee emp = employeeService.login(employee);

        if(emp != null){
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("empNo", employee.getEmpNo());
            claims.put("empName", employee.getEmpName());
            String jwts = JwtsUtil.encodeJwts(claims);
            return Result.success(jwts);
        }
        return Result.error("用户名或密码错误");
    }
}
