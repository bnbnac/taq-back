package sj.taqback.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import sj.taqback.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJpa implements UserRepository {
    private final EntityManager em;
    public UserRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByAccountId(String accountId) {
        List<User> result = em.createQuery("select u from User u where u.accountId = :accountId", User.class)
                .setParameter("accountId", accountId)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        List<User> result = em.createQuery("select u from User u where u.nickname = :nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();

        return result.stream().findAny();
    }
}
