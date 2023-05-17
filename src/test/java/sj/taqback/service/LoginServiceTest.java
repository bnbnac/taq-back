package sj.taqback.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sj.taqback.controller.LoginForm;
import sj.taqback.domain.User;
import sj.taqback.repository.UserRepository;

@SpringBootTest
public class LoginServiceTest {
    @Autowired
    LoginService loginService;
    @Autowired
    UserRepository userRepository;

    @Test
    void tryNotExistIdLogin() {
        LoginForm form = new LoginForm();
        form.setAccountId("id");

        Assertions.assertThatThrownBy(() -> loginService.login(form))
                .hasMessageContaining("아이디");
    }

    @Test
    @Transactional
    void tryNotMatchPasswordLogin() {
        User user = new User();
        user.setAccountId("id");
        user.setPassword("pw");
        userRepository.save(user);

        LoginForm form = new LoginForm();
        form.setAccountId("id");
        form.setPassword("not match");

        Assertions.assertThatThrownBy(() -> loginService.login(form))
                .hasMessageContaining("비밀번호");
    }
}
