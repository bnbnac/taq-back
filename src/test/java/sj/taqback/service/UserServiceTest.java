package sj.taqback.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sj.taqback.domain.User;
import sj.taqback.repository.UserRepository;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void createAccountAndFindUser() {
        User user = new User();
        user.setAccountId("acid");
        user.setNickname("nick");
        user.setPassword("pswd");
        userService.createAccount(user);

        User foundUserByAccountId = userService.findByAccountId("acid").get();
        User foundUserByNickname = userService.findByNickname("nick").get();
        Assertions.assertThat(user.getNickname()).isEqualTo(foundUserByAccountId.getNickname());
        Assertions.assertThat(user.getAccountId()).isEqualTo(foundUserByNickname.getAccountId());
    }

    @Test
    void duplicatedAccountId() {
        User exists = new User();
        exists.setAccountId("bnac");
        exists.setPassword("p");
        userService.createAccount(exists);

        User user = new User();
        user.setAccountId("bnac");
        user.setPassword("p");
        Assertions.assertThatThrownBy(() -> userService.createAccount(user))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void storeUserPasswordEncoded() {
        User user = new User();
        String password = "pswd";
        user.setAccountId("acid");
        user.setPassword(password);

        String userPassword = user.getPassword();

        userService.createAccount(user);
        String repositoryPassword = userService.findByAccountId("acid").get().getPassword();
        Assertions.assertThat(password).isNotEqualTo(repositoryPassword);
    }
}
