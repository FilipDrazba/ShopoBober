package pl.edu.pb.wi.authservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pb.wi.authservice.dtos.LoginRequestDto;
import pl.edu.pb.wi.authservice.dtos.JwtValueDto;
import pl.edu.pb.wi.authservice.dtos.RegisterRequestDto;
import pl.edu.pb.wi.authservice.services.AuthService;
import pl.edu.pb.wi.common.dtos.UserInfoDto;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<JwtValueDto> register(@Valid
                                                @RequestBody
                                                RegisterRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<JwtValueDto> login(@Valid
                                             @RequestBody
                                             LoginRequestDto request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("validate")
    public ResponseEntity<UserInfoDto> validate(@Valid
                                                @RequestBody
                                                JwtValueDto request) {
        var userInfo = authService.validateAndExtract(request);
        return ResponseEntity.ok(userInfo);
    }
}
