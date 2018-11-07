package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Media entity.
 * @author Dheeraj das.
 */
@ApiModel(description = "Media entity. @author Dheeraj das.")
@Entity
@Table(name = "media")
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "url")
    private String url;

    @Column(name = "extension")
    private String extension;

    @ManyToOne
    @JsonIgnoreProperties("proofs")
    private Need need;

    @ManyToOne
    @JsonIgnoreProperties("proofs")
    private Help help;

    @ManyToOne
    @JsonIgnoreProperties("attachments")
    private NewsFeed newsFeed;

    @ManyToOne
    @JsonIgnoreProperties("proofs")
    private Violation violation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public Media fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public Media url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtension() {
        return extension;
    }

    public Media extension(String extension) {
        this.extension = extension;
        return this;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Need getNeed() {
        return need;
    }

    public Media need(Need need) {
        this.need = need;
        return this;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    public Help getHelp() {
        return help;
    }

    public Media help(Help help) {
        this.help = help;
        return this;
    }

    public void setHelp(Help help) {
        this.help = help;
    }

    public NewsFeed getNewsFeed() {
        return newsFeed;
    }

    public Media newsFeed(NewsFeed newsFeed) {
        this.newsFeed = newsFeed;
        return this;
    }

    public void setNewsFeed(NewsFeed newsFeed) {
        this.newsFeed = newsFeed;
    }

    public Violation getViolation() {
        return violation;
    }

    public Media violation(Violation violation) {
        this.violation = violation;
        return this;
    }

    public void setViolation(Violation violation) {
        this.violation = violation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Media media = (Media) o;
        if (media.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), media.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Media{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", url='" + getUrl() + "'" +
            ", extension='" + getExtension() + "'" +
            "}";
    }
}
