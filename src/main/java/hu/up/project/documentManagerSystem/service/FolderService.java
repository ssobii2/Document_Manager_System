package hu.up.project.documentManagerSystem.service;

import hu.up.project.documentManagerSystem.error.EntityNotFoundException;
import hu.up.project.documentManagerSystem.model.FolderDTO;

import java.util.List;

public interface FolderService {
    List<FolderDTO> findAll();

    FolderDTO save(FolderDTO dto) throws EntityNotFoundException;

    FolderDTO update(FolderDTO dto) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}
