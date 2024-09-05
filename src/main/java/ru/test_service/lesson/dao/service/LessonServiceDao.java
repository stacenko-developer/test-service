package ru.test_service.lesson.dao.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test_service.common.dao.AbstractServiceDao;
import ru.test_service.lesson.dao.entity.Lesson;
import ru.test_service.lesson.dao.repository.LessonRepository;

@Service
public class LessonServiceDao extends AbstractServiceDao<Lesson, LessonRepository> {

    protected LessonServiceDao(LessonRepository repository) {
        super(repository);
    }

    @Transactional
    public Lesson findByName(String name) {
        return this.repository.findByName(name);
    }
}
