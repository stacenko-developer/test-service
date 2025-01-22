package ru.test_service.lesson.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import ru.test_service.common.dao.BaseEntity;

import java.io.Serial;

@Getter
@Setter
@Entity
@Comment("Урок")
@Table(schema = "test", name = "tr_lesson")
public class Lesson extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -697116690575840701L;

    @Column(nullable = false)
    private String name;
}
