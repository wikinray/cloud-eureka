package com.xmylx.cloud.consul.gateway.filter;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JwtUtil {

    private static final String SECRET="test";
    private static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_AUTH="Authorization";


    public static String generaeToken(String user){
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",new Random().nextInt());
        map.put("user",user);
        String jwt=Jwts.builder()
                .setSubject("user info")
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
        String finalJwt=TOKEN_PREFIX+jwt;
        return finalJwt;
    }



    public static Map<String,String> validateToken(String token){
        if(token!=null){
            HashMap<String,String> map=new HashMap<>();
            Map<String,Object> object=Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                    .getBody();
            String id=String.valueOf(object.get("id"));
            String user=String.valueOf(object.get("user"));
            map.put("id",id);
            map.put("user",user);
            if(StringUtils.isEmpty(user)){
                throw new JwtException("user is error,please check");
            }
            return map;
        }else{
            throw new JwtException("token is error ,please check");
        }
    }
}
