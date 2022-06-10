package hu.up.project.documentManagerSystem.service;

import hu.up.project.documentManagerSystem.error.EntityNotFoundException;
import hu.up.project.documentManagerSystem.model.DocumentDTO;

import java.util.List;

public interface DocumentService {
    List<DocumentDTO> findAll();

    DocumentDTO save(DocumentDTO dto) throws EntityNotFoundException;

    DocumentDTO update(DocumentDTO dto) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}
