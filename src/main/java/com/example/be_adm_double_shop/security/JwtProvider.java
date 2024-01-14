package com.example.be_adm_double_shop.security;

import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.entity.google.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtProvider {


    @Value("${spring.security.jwt.secret_key}")
    private String SECRET_KEY;

    @Value("${spring.security.jwt.expiration_time}")
    private Integer EXPIRATION_TIME;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromTokenByKey(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromTokenByKey(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Object getAllClaimsFromToken(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserInfo payloadObject = objectMapper.readValue(payload, UserInfo.class);
            return payloadObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", employee.getUsername());
        claims.put("name", employee.getName());
        claims.put("email", employee.getEmail());
        claims.put("phone", employee.getPhone());
        claims.put("district", employee.getDistrict());
        claims.put("provice", employee.getProvice());
        claims.put("city", employee.getCity());
        claims.put("gender", employee.getGender());
        claims.put("birth_day", employee.getBirthDay());
        claims.put("role", employee.getRole());
        claims.put("status", employee.getStatus());
        claims.put("created_by", employee.getCreatedBy());
        claims.put("updated_by", employee.getUpdatedBy());
        claims.put("created_time", employee.getCreatedTime());
        claims.put("updated_time", employee.getUpdatedTime());
        return doGenerateToken(claims);
    }

    private String doGenerateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
