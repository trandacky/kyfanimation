package com.dacky.service.dto;

import com.dacky.domain.KeyValueData;
import java.time.Instant;

public class KeyValueDTO {

    private Long id;
    private String key;
    private String value;
    private Instant createTime;
    private Instant updateTime;

    public KeyValueDTO() {}

    public KeyValueDTO(KeyValueData keyValueData) {
        this.id = keyValueData.getId();
        this.key = keyValueData.getKey();
        this.value = keyValueData.getValue();
        this.createTime = keyValueData.getCreatedDate();
        this.updateTime = keyValueData.getLastModifiedDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }
}
