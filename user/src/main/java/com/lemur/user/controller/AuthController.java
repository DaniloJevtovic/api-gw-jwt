package com.lemur.user.controller;

import com.lemur.user.dto.LoginDTO;
import com.lemur.user.dto.RegisterDTO;
import com.lemur.user.security.JwtUtil;
import com.lemur.user.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO dto) {
        if (userService.saveUser(dto) == null)
            return ResponseEntity.badRequest().body("Korisnik sa tom adresom vec postoji");

        return ResponseEntity.ok("Korisnik uspjesno registrovan");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        if (userService.checkCredentials(loginDTO))
            return new ResponseEntity<>(jwtUtil.generateToken(loginDTO.email()), HttpStatus.OK);

        return new ResponseEntity<>("neispranvi kredencijali", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/validateToken")
    public Boolean validateToken(@RequestBody String token) {
        log.info("validacija...");
        return jwtUtil.validateToken(token);
    }
}
