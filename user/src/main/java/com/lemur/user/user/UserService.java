package com.lemur.user.user;

import com.lemur.user.dto.LoginDTO;
import com.lemur.user.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(RegisterDTO registerDTO) {
        User user = getUserByEmail(registerDTO.email());

        if (user != null) {
            log.error("korisnik {} vec postoji", registerDTO.email());
            return null;
        }

        User newUser = User.builder()
                .name(registerDTO.name())
                .email(registerDTO.email())
                .password(passwordEncoder.encode(registerDTO.password()))
                .build();

        return newUser;
    }

    public Boolean checkCredentials(LoginDTO loginDTO) {
        User user = getUserByEmail(loginDTO.email());

        if (user != null && passwordEncoder.matches(loginDTO.password(), user.getPassword()))
            return true;

        return false;
    }


}
