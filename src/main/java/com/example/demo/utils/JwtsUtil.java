package com.example.demo.utils;

import com.example.demo.pojo.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtsUtil {
    private static Key key = Keys.hmacShaKeyFor("herrylo-secret-key-32-bytes-long-12345678".getBytes()); // 密钥
    private static int expiration = 3600 * 1000; // 有效时长1h

    /**
     * 生成jwt
     * @param claims 自定义数据
     * @return jwt令牌
     */
    public static String encodeJwts(HashMap claims) {
        String Jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,key) // 签名算法
                .setClaims(claims) // 自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + expiration))  // 设置有效期1小时
                .compact();
        return Jwt;
    }

    /**
     * 解析jwt
     * @param token jwt令牌
     * @return
     */
    public static Claims parseJwts(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
