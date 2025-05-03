package pl.edu.pb.wi.authservice.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.authservice.dtos.RegisterRequestDto;
import pl.edu.pb.wi.authservice.entities.User;
import pl.edu.pb.wi.authservice.mappers.UserMapper;
import pl.edu.pb.wi.authservice.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public User createUser(RegisterRequestDto userDto) {
        User user = userMapper.toUser(userDto);

        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
