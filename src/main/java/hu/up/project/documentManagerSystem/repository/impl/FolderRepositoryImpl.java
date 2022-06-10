package hu.up.project.documentManagerSystem.repository.impl;

import hu.up.project.documentManagerSystem.entity.FolderEntity;
import hu.up.project.documentManagerSystem.repository.FolderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FolderRepositoryImpl extends CoreRepositoryImpl<FolderEntity> implements FolderRepository {
    public FolderRepositoryImpl() {
    }
    @Override
    protected Class<FolderEntity> getManagedClass() {
        return FolderEntity.class;
    }
}
