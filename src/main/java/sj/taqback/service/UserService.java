package sj.taqback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sj.taqback.domain.User;
import sj.taqback.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createAccount(User user) {
        userRepository.save(user);

        return user.getId();
    }

    public Optional<User> findByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }
}
