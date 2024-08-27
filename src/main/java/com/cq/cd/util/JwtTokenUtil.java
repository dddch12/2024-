package com.cq.cd.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

	private static String secret = "w@9Kfj3^z8Y$+2mLQeR!jN7pTzXbVr3@HcFhGaY%2U^sZr8OqP";

	private static long expiration = 604800;

	// 生成JWT令牌
	public static String generateToken(String username) {
		return Jwts.builder()
				.setHeaderParam("type", "JWT")
				.setSubject(username) // 设置令牌的主体（用户名）
				.setIssuedAt(new Date()) // 设置令牌的签发时间
				.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000)) // 设置令牌的过期时间
				.signWith(SignatureAlgorithm.HS512, secret) // 使用HS512算法和密钥签名令牌
				.compact();
	}

	// 从JWT中获取用户名
	public static Claims getUsernameFromToken(String token) {
		return	 Jwts.parser()
				.setSigningKey(secret) // 设置用于解析的密钥
				.parseClaimsJws(token) // 解析令牌
				.getBody();
	}

	// 验证JWT的有效性
    // 验证JWT并返回状态
    public static Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true; // 如果验证通过，返回valid
        } catch (ExpiredJwtException e) {
            return false;// 如果令牌已过期，返回expired
        } catch (JwtException e) {
            return false; // 如果令牌无效，返回invalid
        }
    }

//	// 检查JWT是否过期
//	private boolean isTokenExpired(String token) {
//		Date expirationDate = Jwts.parser()
//				.setSigningKey(secret) // 设置用于解析的密钥
//				.parseClaimsJws(token) // 解析令牌
//				.getBody()
//				.getExpiration(); // 获取过期时间
//		return expirationDate.before(new Date()); // 判断是否过期
//	}
}
