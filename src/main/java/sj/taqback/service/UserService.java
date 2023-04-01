package sj.taqback.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sj.taqback.domain.User;
import sj.taqback.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long createAccount(User user) {
        checkAccountIdDuplicated(user);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return user.getId();
    }

    private void checkAccountIdDuplicated(User user) {
        userRepository.findByAccountId(user.getAccountId())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 사용중인 아이디입니다.");
                });
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }
}
