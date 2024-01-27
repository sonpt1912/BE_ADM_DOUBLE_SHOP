package com.example.be_adm_double_shop.controller.auth;

import com.example.be_adm_double_shop.dto.JwtResponse;
import com.example.be_adm_double_shop.dto.TokenRequest;
import com.example.be_adm_double_shop.dto.UserRequest;
import com.example.be_adm_double_shop.dto.ValidationException;
import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.entity.google.UserInfo;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.EmployeeService;
import com.example.be_adm_double_shop.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private EmployeeService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody Employee employee) {
        return null;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequest request) {
        try {
            Employee user = userService.findUserbyUsername(request.getUsername());
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = jwtProvider.generateToken(user);
                Map<String, Object> data = new HashMap<>();
                data.put("code", HttpStatus.OK.value());
                data.put("status", HttpStatus.OK.getReasonPhrase());
                data.put("message", Constant.SUCCESS);
                JwtResponse response = JwtResponse.builder()
                        .access_token(token).build();
                data.put("data", response);
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                throw new ValidationException(Constant.UNAUTHORIZED, Constant.USERNAME_OR_PASSWORD);
            }
        } catch (Exception e) {
            throw new ValidationException(Constant.UNAUTHORIZED, Constant.USERNAME_OR_PASSWORD);
        }

    }

    @PostMapping("/google")
    public Object loginGoogle(@RequestBody TokenRequest crenditial) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserInfo user = (UserInfo) jwtProvider.getAllClaimsFromToken(crenditial.getCrenditial());
        Employee employee = userService.findUserbyUsername(user.getEmail());
        JwtResponse response = JwtResponse.builder()
                .access_token(jwtProvider.generateToken(employee)).build();
        return response;
    }


}
