package pl.edu.pb.wi.authservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.authservice.dtos.JwtValueDto;
import pl.edu.pb.wi.authservice.dtos.LoginRequestDto;
import pl.edu.pb.wi.authservice.dtos.RegisterRequestDto;
import pl.edu.pb.wi.authservice.entities.User;
import pl.edu.pb.wi.authservice.mappers.UserMapper;
import pl.edu.pb.wi.common.dtos.UserInfoDto;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public JwtValueDto register(RegisterRequestDto request) {
        String encodedPassword = encoder.encode(request.password());

        RegisterRequestDto userWithEncodedPasswordDto =
                RegisterRequestDto.builder()
                        .email(request.email())
                        .password(encodedPassword)
                        .role(request.role())
                        .build();

        User savedUser = userService.createUser(userWithEncodedPasswordDto);

        return jwtService.generateToken(savedUser);
    }

    public JwtValueDto authenticate(LoginRequestDto request) {
        User user = userService.getUserByEmail(request.email());

        if (!encoder.matches(request.password(), user.getPassword()))
            throw new BadCredentialsException("Incorrect login details");

        return jwtService.generateToken(user);
    }

    public UserInfoDto validateAndExtract(JwtValueDto request) {
        String token = request.token();

        if (!jwtService.isTokenValid(token))
            throw new BadCredentialsException("Invalid request");

        String email = jwtService.extractEmail(token);
        User user = userService.getUserByEmail(email);

        return userMapper.toUserInfoDto(user);
    }
}
