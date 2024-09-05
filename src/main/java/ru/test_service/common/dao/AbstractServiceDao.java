package ru.test_service.common.dao;


import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.test_service.common.exception.NotFoundException;
import java.util.UUID;

public abstract class AbstractServiceDao<E extends BaseEntity, T extends AbstractRepository<E>> {

    protected  final T repository;

    protected AbstractServiceDao(T repository) {
        this.repository = repository;
    }

    @Transactional
    public E findById(UUID id) {
        if (id == null) {
            throw new NotFoundException();
        }

        E entity = repository.findById(id).orElse(null);

        if (entity == null) {
            throw new NotFoundException();
        }

        return entity;
    }

    @Transactional
    public Page<E> findAll(Pageable pageable) {
        if (pageable == null) {
            return Page.empty();
        }

        return repository.findAll(pageable);
    }

    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }
}
