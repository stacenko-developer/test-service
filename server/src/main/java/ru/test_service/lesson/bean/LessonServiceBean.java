package ru.test_service.lesson.bean;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test_service.lesson.dao.entity.Lesson;
import ru.test_service.lesson.dao.service.LessonServiceDao;
import ru.test_service.lesson.dto.CreateLessonDto;
import ru.test_service.lesson.dto.LessonDto;
import ru.test_service.lesson.exception.LessonNameHasAlreadyExistException;
import ru.test_service.lesson.exception.LessonNameNotSpecifiedException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LessonServiceBean {

    private final LessonServiceDao lessonServiceDao;
    private final ModelMapper mapper;

    @Transactional
    public LessonDto createLesson(CreateLessonDto lesson) {
        if (StringUtils.isBlank(lesson.getName())) {
            throw new LessonNameNotSpecifiedException();
        }

        if (lessonServiceDao.findByName(lesson.getName()) != null) {
            throw new LessonNameHasAlreadyExistException();
        }

        LessonDto result = mapper
                .map(lessonServiceDao.save(mapper.map(lesson, Lesson.class)),
                        LessonDto.class);

        return result;
    }

    @Transactional
    public LessonDto updateLesson(LessonDto lessonDto) {
        Lesson entity = lessonServiceDao.findById(lessonDto.getId());

        if (StringUtils.isBlank(lessonDto.getName())) {
            throw new LessonNameNotSpecifiedException();
        }

        Lesson foundLesson = lessonServiceDao.findByName(lessonDto.getName());

        if (foundLesson != null && !foundLesson.getId().equals(lessonDto.getId())) {
            throw new LessonNameHasAlreadyExistException();
        }

        entity.setName(lessonDto.getName());

        LessonDto result = mapper
                .map(lessonServiceDao.save(entity),
                        LessonDto.class);

        return result;
    }

    @Transactional
    public LessonDto findById(UUID id) {
        Lesson lesson = lessonServiceDao.findById(id);

        return mapper.map(lesson, LessonDto.class);
    }

    @Transactional
    public void deleteLesson(UUID id) {
        lessonServiceDao.deleteById(id);
    }
}