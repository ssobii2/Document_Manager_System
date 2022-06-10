package hu.up.project.documentManagerSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "folder")
@Entity
public class FolderEntity extends CoreEntity {
    @Column(name = "color")
    private String color;
    @Column(name = "parentId")
    private Long parentId;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
