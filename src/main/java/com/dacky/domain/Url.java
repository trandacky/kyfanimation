package com.dacky.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("url")
public class Url extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_episode")
    private Long idEpisode;

    @Column(name = "release_time")
    private Instant releaseTime = Instant.now();

    @Column(name = "limit_time_release")
    private Instant limitTimeRelease = Instant.now();

    @Column(name = "active")
    private boolean active;

    @Column(name = "url", length = 1024)
    private String url;

    @Column(name = "link_type")
    private String linkType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEpisode() {
        return idEpisode;
    }

    public void setIdEpisode(Long idEpisode) {
        this.idEpisode = idEpisode;
    }

    public Instant getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Instant releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Instant getLimitTimeRelease() {
        return limitTimeRelease;
    }

    public void setLimitTimeRelease(Instant limitTimeRelease) {
        this.limitTimeRelease = limitTimeRelease;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }
}
