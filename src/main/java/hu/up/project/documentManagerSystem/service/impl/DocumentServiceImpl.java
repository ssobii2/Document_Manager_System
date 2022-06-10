package hu.up.project.documentManagerSystem.service.impl;

import hu.up.project.documentManagerSystem.entity.DocumentEntity;
import hu.up.project.documentManagerSystem.error.EntityNotFoundException;
import hu.up.project.documentManagerSystem.model.DocumentDTO;
import hu.up.project.documentManagerSystem.repository.DocumentRepository;
import hu.up.project.documentManagerSystem.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository repository;

    @Override
    public List<DocumentDTO> findAll() {
        List<DocumentDTO> list = new ArrayList<>();
        List<DocumentEntity> entityList = repository.findAll();
        entityList.forEach(entity -> {
            DocumentDTO dto = new DocumentDTO();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setName(entity.getName());
            dto.setUrl(entity.getUrl());
            dto.setType(entity.getType());
            if (entity.getParentId() != null) {
                dto.setParentId(entity.getParentId());
            }
            list.add(dto);
        });
        return list;
    }
    @Override
    public DocumentDTO save(DocumentDTO dto) {
        DocumentEntity entity = new DocumentEntity();
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());
        entity.setType(dto.getType());
        entity.setParentId(dto.getParentId());
        repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    @Override
    public DocumentDTO update(DocumentDTO dto) throws EntityNotFoundException {
        DocumentEntity entity = repository.findById(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());
        entity.setType(dto.getType());
        entity.setParentId(dto.getParentId());
        repository.update(entity);
        return dto;
    }
    @Override
    public void delete(Long id) throws EntityNotFoundException {
        repository.delete(id);
    }
}
