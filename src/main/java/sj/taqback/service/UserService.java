package sj.taqback.service;

import org.springframework.stereotype.Service;
import sj.taqback.domain.User;
import sj.taqback.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createAccount(User user) {
        checkAccountIdDuplicated(user);
        userRepository.save(user);
        return user.getId();
    }

    private void checkAccountIdDuplicated(User user) {
        userRepository.findByAccountId(user.getAccountId())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 사용중인 아이디입니다.");
                });
    }

    public Optional<User> findByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }
}
