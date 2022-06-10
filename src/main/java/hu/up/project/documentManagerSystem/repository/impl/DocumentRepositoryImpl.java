package hu.up.project.documentManagerSystem.repository.impl;

import hu.up.project.documentManagerSystem.entity.DocumentEntity;
import hu.up.project.documentManagerSystem.repository.DocumentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl extends CoreRepositoryImpl<DocumentEntity> implements DocumentRepository {
    public DocumentRepositoryImpl() {
    }

    @Override
    protected Class<DocumentEntity> getManagedClass() {
        return DocumentEntity.class;
    }
}
