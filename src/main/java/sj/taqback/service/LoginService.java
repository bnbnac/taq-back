package sj.taqback.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sj.taqback.dto.LoginDto;
import sj.taqback.entity.User;
import sj.taqback.repository.UserRepository;

@Service
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public LoginService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String login(LoginDto loginDto) {
        User user = userRepository.findByAccountId(loginDto.getAccountId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디입니다."));

        if (!matchPassword(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // signiture with JWT and return token
        String token = "";

        return token;
    }

    // 이거 클랳스가 여기 맞는지
    public boolean matchPassword(String inputPassword, String repositoryPassword) {
        return passwordEncoder.matches(inputPassword, repositoryPassword);
    }
}
