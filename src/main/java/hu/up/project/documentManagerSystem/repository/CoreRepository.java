package hu.up.project.documentManagerSystem.repository;

import hu.up.project.documentManagerSystem.entity.CoreEntity;
import hu.up.project.documentManagerSystem.error.EntityNotFoundException;

import java.util.List;

public interface CoreRepository <T extends CoreEntity> {
    List<T> findAll();

    T save(T entity);

    T update(T entity) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;

    T findById(Long id) throws EntityNotFoundException;
}
