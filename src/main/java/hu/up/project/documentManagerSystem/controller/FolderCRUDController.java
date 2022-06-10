package hu.up.project.documentManagerSystem.controller;

import hu.up.project.documentManagerSystem.error.EntityNotFoundException;
import hu.up.project.documentManagerSystem.model.FolderDTO;
import hu.up.project.documentManagerSystem.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FolderCRUDController {
    @Autowired
    private FolderService folderService;

    public FolderCRUDController() {
    }

    @GetMapping("/api/folder")
    public ResponseEntity<List<FolderDTO>> findall() {
        return ResponseEntity.ok(folderService.findAll());
    }

    @PostMapping("/api/folder")
    public ResponseEntity save(@RequestBody FolderDTO entity) throws EntityNotFoundException {
        return ResponseEntity.ok(folderService.save(entity));
    }

    @PutMapping("/api/folder")
    public ResponseEntity update(@RequestBody FolderDTO entity) {
        try {
            return ResponseEntity.ok(folderService.update(entity));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/folder/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            folderService.delete(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
