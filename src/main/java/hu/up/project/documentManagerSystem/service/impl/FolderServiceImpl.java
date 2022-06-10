package hu.up.project.documentManagerSystem.service.impl;

import hu.up.project.documentManagerSystem.entity.FolderEntity;
import hu.up.project.documentManagerSystem.error.EntityNotFoundException;
import hu.up.project.documentManagerSystem.model.FolderDTO;
import hu.up.project.documentManagerSystem.repository.FolderRepository;
import hu.up.project.documentManagerSystem.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderRepository repository;

    @Override
    public List<FolderDTO> findAll() {
        List<FolderDTO> list = new ArrayList<>();
        List<FolderEntity> entityList = repository.findAll();
        entityList.forEach(folderEntity -> {
            FolderDTO dto = new FolderDTO();
            dto.setId(folderEntity.getId());
            dto.setDescription(folderEntity.getDescription());
            dto.setName(folderEntity.getName());
            dto.setColor(folderEntity.getColor());
            dto.setParentId(folderEntity.getParentId());
            list.add(dto);
        });
        return list;
    }

    @Override
    public FolderDTO save(FolderDTO dto) {
        FolderEntity folderEntity = new FolderEntity();
        folderEntity.setDescription(dto.getDescription());
        folderEntity.setName(dto.getName());
        folderEntity.setColor(dto.getColor());
        folderEntity.setParentId(dto.getParentId());
        repository.save(folderEntity);
        dto.setId(folderEntity.getId());
        return dto;
    }
    @Override
    public FolderDTO update(FolderDTO dto) throws EntityNotFoundException {
        FolderEntity folderEntity = repository.findById(dto.getId());
        folderEntity.setDescription(dto.getDescription());
        folderEntity.setName(dto.getName());
        folderEntity.setColor(dto.getColor());
        folderEntity.setParentId(dto.getParentId());
        repository.update(folderEntity);
        return dto;
    }
    @Override
    public void delete(Long id) throws EntityNotFoundException {
        repository.delete(id);
    }
}
