package ru.test_service.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AbstractRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {
}
