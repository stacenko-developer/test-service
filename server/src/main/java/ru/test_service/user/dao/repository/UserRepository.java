package ru.test_service.user.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test_service.common.dao.AbstractRepository;
import ru.test_service.user.dao.entity.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, AbstractRepository<User> {
    User findByLogin(String login);

    User findByEmail(String email);
}
