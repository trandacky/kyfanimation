package com.dacky.repository;

import com.dacky.domain.KeyValueData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface KeyValueDataRepository extends R2dbcRepository<KeyValueData, Long> {
    @Query("SELECT kvd FROM key_value_data kvd where kvd.key = :key")
    KeyValueData selectKeyValueWithKey(String key);
}
