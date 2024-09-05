package ru.test_service.user.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test_service.common.dao.AbstractRepository;
import ru.test_service.user.dao.entity.Role;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID>, AbstractRepository<Role> {
    Role findByCode(String code);
}
