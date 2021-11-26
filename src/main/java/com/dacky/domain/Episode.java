package com.dacky.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("episode")
public class Episode extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firm_id")
    private Long firmId;

    @Column(name = "image_url", length = 1024)
    private String imageUrl;

    @Column(name = "name")
    private String name;

    @Column(name = "release_time")
    private Instant releaseTime = Instant.now();

    @Column(name = "limit_time_release")
    private Instant limitTimeRelease = Instant.now();

    @Column(name = "active")
    private boolean active = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }
}
