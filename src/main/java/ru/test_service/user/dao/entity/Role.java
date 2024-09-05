package ru.test_service.user.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.test_service.common.dao.BaseEntity;
import java.io.Serial;

@Getter
@Setter
@Entity
@Component("Роль пользователя")
@Table(schema = "users", name = "tr_role")
public class Role extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -3081189477932420948L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;
}
