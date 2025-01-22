package ru.test_service.user.bean;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test_service.lesson.dao.entity.Lesson;
import ru.test_service.lesson.dao.service.LessonServiceDao;
import ru.test_service.lesson.dto.LessonDto;
import ru.test_service.mail.dto.EmailDto;
import ru.test_service.mail.service.EmailService;
import ru.test_service.user.configuration.RestoreUserConfiguration;
import ru.test_service.user.dao.entity.Role;
import ru.test_service.user.dao.entity.User;
import ru.test_service.user.dao.service.RoleServiceDao;
import ru.test_service.user.dao.service.UserServiceDao;
import ru.test_service.user.dto.RestoreUserDto;
import ru.test_service.user.dto.SecuredUserDto;
import ru.test_service.user.dto.UserDto;
import ru.test_service.user.exception.EmailNotSpecifiedException;
import ru.test_service.user.exception.UserLoginHasAlreadyExistException;
import ru.test_service.user.exception.UserLoginNotSpecifiedException;
import ru.test_service.user.exception.UserPasswordNotSpecifiedException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceBean {

    private final UserServiceDao userServiceDao;
    private final RoleServiceDao roleServiceDao;
    private final LessonServiceDao lessonServiceDao;
    private final PasswordServiceBean passwordServiceBean;
    private final EmailService emailService;
    private final ModelMapper mapper;
    private final RestoreUserConfiguration restoreUserConfiguration;

    @Transactional
    public void restoreUser(RestoreUserDto restoreUserDto) {
        if (StringUtils.isBlank(restoreUserDto.getEmail())) {
            throw new EmailNotSpecifiedException();
        }

        User user = userServiceDao.findByEmail(restoreUserDto.getEmail());
        String newPassword = passwordServiceBean.getRandomPassword();

        EmailDto emailDto = new EmailDto();
        emailDto.setEmailSender(restoreUserConfiguration.getRestoreUserUsername());
        emailDto.setSubject(restoreUserConfiguration.getRestoreUserSubject());
        emailDto.setRecipient(restoreUserDto.getEmail());
        emailDto.setBody(String.format(restoreUserConfiguration.getRestoreUserBody(),
                newPassword));

        emailService.sendEmail(emailDto);

        updateUser(mapper.map(user, SecuredUserDto.class));
    }

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
    public UserDto createUser(SecuredUserDto user) {
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

        List<Lesson> lessons = new ArrayList<>();

        for (LessonDto lessonDto : user.getLessons()) {
            lessons.add(lessonServiceDao.findById(lessonDto.getId()));
        }

        entity.setLessons(lessons);

        UserDto result = mapper
                .map(userServiceDao.save(entity),
                        UserDto.class);

        return result;
    }

    @Transactional
    public UserDto updateUser(SecuredUserDto user) {
        User entity = userServiceDao.findById(user.getId());

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

        List<Lesson> lessons = new ArrayList<>();

        for (LessonDto lessonDto : user.getLessons()) {
            lessons.add(lessonServiceDao.findById(lessonDto.getId()));
        }

        entity.setLessons(lessons);

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
