package sj.taqback.repository;

import sj.taqback.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByAccountId(String accountId);

    List<User> findAll();
}
