package sj.taqback.repository;

import sj.taqback.entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByAccountId(String accountId);

    Optional<User> findByNickname(String nickname);
}
