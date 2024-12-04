package ru.test_service.lesson.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test_service.common.dao.AbstractRepository;
import ru.test_service.lesson.dao.entity.Lesson;

import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID>, AbstractRepository<Lesson> {
    Lesson findByName(String name);
}
