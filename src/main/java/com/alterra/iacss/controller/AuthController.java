package com.alterra.iacss.controller;

import com.alterra.iacss.domain.dto.LoginForm;
import com.alterra.iacss.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody LoginForm form){
       return userService.register(form);
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken (@RequestBody LoginForm form){
        return ResponseEntity.ok(userService.generateToken(form));
    }
}
