package hu.up.project.documentManagerSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "document")
@Entity
public class DocumentEntity extends CoreEntity {
    @NotNull
    @Column(name = "url")
    private String url;
    @NotNull
    @Column(name = "type")
    private String type;
    @NotNull
    @Column(name = "parentId")
    private Long parentId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
