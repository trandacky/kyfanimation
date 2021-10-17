package com.dacky.repository;

import com.dacky.domain.KeyValueData;
import java.util.List;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface KeyValueDataRepository extends R2dbcRepository<KeyValueData, Long> {
    @Query("SELECT id, key, value FROM key_value_data where key = 'notification'")
    Mono<KeyValueData> findByKey();

    @Query("UPDATE key_value_data SET value = :value WHERE key = :key")
    Mono<Void> updateKeyValue(String value, String key);

    Mono<KeyValueData> findOneByKey(String key);

    Flux<KeyValueData> findAllByKeyIn(List keys);
}
