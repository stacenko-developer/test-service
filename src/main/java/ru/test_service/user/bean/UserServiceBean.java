package ru.test_service.user.bean;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test_service.user.dao.entity.Role;
import ru.test_service.user.dao.entity.User;
import ru.test_service.user.dao.service.RoleServiceDao;
import ru.test_service.user.dao.service.UserServiceDao;
import ru.test_service.user.dto.CreateUserDto;
import ru.test_service.user.dto.SecuredUserDto;
import ru.test_service.user.dto.UserDto;
import ru.test_service.user.exception.UserLoginHasAlreadyExistException;
import ru.test_service.user.exception.UserLoginNotSpecifiedException;
import ru.test_service.user.exception.UserPasswordNotSpecifiedException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceBean {

    private final UserServiceDao userServiceDao;
    private final RoleServiceDao roleServiceDao;
    private final ModelMapper mapper;

    @Transactional
    public UserDto findByLogin(String login) {
        User user = userServiceDao.findByLogin(login);

        if (user == null) {
            return null;
        }

        return mapper.map(user, UserDto.class);
    }

    @Transactional
    public SecuredUserDto findSecuredByLogin(String login) {
        User user = userServiceDao.findByLogin(login);

        if (user == null) {
            return null;
        }

        return mapper.map(user, SecuredUserDto.class);
    }

    @Transactional
    public UserDto createUser(CreateUserDto user) {
        if (StringUtils.isBlank(user.getLogin())) {
            throw new UserLoginNotSpecifiedException();
        }

        if (StringUtils.isBlank(user.getPassword())) {
            throw new UserPasswordNotSpecifiedException();
        }

        if (userServiceDao.findByLogin(user.getLogin()) != null) {
            throw new UserLoginHasAlreadyExistException();
        }

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        User entity = mapper.map(user, User.class);
        Role role = roleServiceDao.findByCode("STUDENT");

        if (role != null) {
            entity.setRoles(List.of(role));
        }

        UserDto result = mapper
                .map(userServiceDao.save(entity),
                        UserDto.class);

        return result;
    }

    @Transactional
    public UserDto updateUser(SecuredUserDto user) {
        User entity = userServiceDao.findById(user.getId());

            int f = 4;



        if (StringUtils.isNotBlank(user.getPassword())) {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            entity.setPassword(encoder.encode(user.getPassword()));
        }

        if (StringUtils.isBlank(user.getLogin())) {
            throw new UserLoginNotSpecifiedException();
        }

        User foundUser = userServiceDao.findByLogin(user.getLogin());

        if (foundUser != null && !foundUser.getId().equals(user.getId())) {
            throw new UserLoginHasAlreadyExistException();
        }

        entity.setLogin(user.getLogin());

        UserDto result = mapper
                .map(userServiceDao.save(entity),
                        UserDto.class);

        return result;
    }

    @Transactional
    public UserDto findById(UUID id) {
        User user = userServiceDao.findById(id);

        return mapper.map(user, UserDto.class);
    }

    @Transactional
    public void deleteUser(UUID id) {
        userServiceDao.deleteById(id);
    }
}
