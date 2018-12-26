//package com.bing.authorization.util;
//
//
//
//import com.bing.entity.TokenUser;
//import org.joda.time.LocalDateTime;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtUtil implements ApplicationListener {
//
//  private static String secret = "8136264078597545";
//  private static int expirationHour = 24;
//
//  public static TokenUser parseToken(String token) {
//    try {
//      Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//      TokenUser u = new TokenUser();
//      u.setName(body.getSubject());
//      u.setUid((Integer) body.get("uid"));
//      u.setLoginType((Integer) body.get("loginType"));
//      return u;
//    } catch (JwtException e) {
//      return null;
//    }
//  }
//
//  public static String generateToken(TokenUser u) {
//    Claims claims = Jwts.claims().setSubject(u.getName());
//    claims.put("uid", u.getUid());
//    claims.setExpiration(LocalDateTime.now().plusHours(expirationHour).toDate());
//    claims.put("loginType", u.getLoginType());
//    return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
//  }
//
//  public String getSecret() {
//    return secret;
//  }
//
//  public void setSecret(String secret) {
//    this.secret = secret;
//  }
//
//  public int getExpirationHour() {
//    return expirationHour;
//  }
//
//  public void setExpirationHour(int expirationHour) {
//    this.expirationHour = expirationHour;
//  }
//
//  public static void main(String[] args) {
//    TokenUser user = new TokenUser();
//    user.setUid(222);
//    user.setName("wangxingling");
//    user.setLoginType(1);
//    JwtUtil jwtUtil = new JwtUtil();
//    String generateToken = jwtUtil.generateToken(user);
//    System.out.println(generateToken);
//    TokenUser parseToken = jwtUtil.parseToken(generateToken);
//    System.out.println(parseToken);
//  }
//
//  @Override
//  public void onApplicationEvent(ApplicationEvent applicationEvent) {
//    System.out.println("111");
//  }
//}
