package com.example.be_adm_double_shop.controller.auth;

import com.example.be_adm_double_shop.dto.JwtRequest;
import com.example.be_adm_double_shop.dto.JwtResponse;
import com.example.be_adm_double_shop.dto.TokenRequest;
import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.entity.google.UserInfo;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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

    @PostMapping("/create-account")
    public ResponseEntity create(@RequestBody Employee user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody Employee employee) {
        return null;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtProvider.generateToken(userDetails);
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/google")
    public Object loginGoogle(@RequestBody TokenRequest crenditial) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserInfo user = (UserInfo) jwtProvider.getAllClaimsFromToken(crenditial.getCrenditial());
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        JwtResponse response = JwtResponse.builder()
                .jwtToken(jwtProvider.generateToken(userDetails))
                .username(userDetails.getUsername()).build();
        return response;
    }


}
