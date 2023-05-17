package sj.taqback.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sj.taqback.controller.LoginForm;
import sj.taqback.domain.User;

@Service
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public LoginService(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public String login(LoginForm form) {
        User user = userService.findByAccountId(form.getAccountId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디입니다."));

        if (!matchPassword(form.getPassword(), user.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // signiture with JWT and return token
        String token = "";

        return token;
    }

    public boolean matchPassword(String inputPassword, String repositoryPassword) {
        return passwordEncoder.matches(inputPassword, repositoryPassword);
    }
}
