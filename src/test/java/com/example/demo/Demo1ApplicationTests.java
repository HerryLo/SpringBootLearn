package com.example.demo;

import com.example.demo.pojo.Department;
import com.example.demo.utils.TestAutowired;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Demo1ApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 生成jwt
     */
    @Test
    public void testJwts() {
        // jwt官网： https://www.jwt.io/
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "test3");

        Key key = Keys.hmacShaKeyFor("herrylo-secret-key-32-bytes-long-12345678".getBytes());

        String Jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,key) // 签名算法
                .setClaims(claims) // 自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))  // 设置有效期1小时
                .compact();
        System.out.println(Jwt);
    }

    /**
     * 解析jwt
     */
    @Test
    public void testParseJwts() {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor("herrylo-secret-key-32-bytes-long-12345678".getBytes()))
                .build()
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidGVzdDMiLCJpZCI6MSwiZXhwIjoxNzczOTc1ODc0fQ.EjsEyhCSrb9NSg2k0yjXXL5hY2G_bH2DLSj6OInzi18")
                .getBody();
        System.out.println(claims);
    }

    @Autowired
    TestAutowired testAutowired;

    @Test
    public void testAutowired() {
        testAutowired.print();
    }
}
