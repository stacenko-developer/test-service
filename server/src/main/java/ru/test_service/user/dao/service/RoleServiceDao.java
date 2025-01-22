package ru.test_service.user.dao.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test_service.common.dao.AbstractServiceDao;
import ru.test_service.user.dao.entity.Role;
import ru.test_service.user.dao.repository.RoleRepository;

@Service
public class RoleServiceDao extends AbstractServiceDao<Role, RoleRepository> {
    protected RoleServiceDao(RoleRepository repository) {
        super(repository);
    }

    @Transactional
    public Role findByCode(String code) {
        return this.repository.findByCode(code);
    }
}
