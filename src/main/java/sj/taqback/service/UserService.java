package sj.taqback.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sj.taqback.entity.User;
import sj.taqback.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
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

        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return user.getId();
    }

    public void checkAccountIdDuplicated(User user) {
        userRepository.findByAccountId(user.getAccountId())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 사용중인 아이디입니다.");
                });
    }

    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findOne(String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }
}
