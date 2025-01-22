package ru.test_service.user.dao.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test_service.common.dao.AbstractServiceDao;
import ru.test_service.common.exception.NotFoundException;
import ru.test_service.user.dao.entity.User;
import ru.test_service.user.dao.repository.UserRepository;

@Service
public class UserServiceDao extends AbstractServiceDao<User, UserRepository> {

    protected UserServiceDao(UserRepository repository) {
        super(repository);
    }

    @Transactional
    public User findByLogin(String login) {
        return this.repository.findByLogin(login);
    }

    @Transactional
    public User findByEmail(String email) {
        User user = this.repository.findByEmail(email);

        if (user == null) {
            throw new NotFoundException();
        }

        return user;
    }
}