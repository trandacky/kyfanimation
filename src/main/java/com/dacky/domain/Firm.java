package com.dacky.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("firm")
public class Firm extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url", length = 1024)
    private String imageUrl;

    @Column(name = "introduce", length = 2048)
    private String introduce;

    @Column(name = "count_episode")
    private int countEpisode = 0;

    @Column(name = "release_episode")
    private int releaseEpisode = 0;

    @Column(name = "release_time")
    private Instant releaseTime = Instant.now();

    @Column(name = "active")
    private boolean active = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCountEpisode() {
        return countEpisode;
    }

    public void setCountEpisode(int countEpisode) {
        this.countEpisode = countEpisode;
    }

    public int getReleaseEpisode() {
        return releaseEpisode;
    }

    public void setReleaseEpisode(int releaseEpisode) {
        this.releaseEpisode = releaseEpisode;
    }

    public Instant getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Instant releaseTime) {
        this.releaseTime = releaseTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
