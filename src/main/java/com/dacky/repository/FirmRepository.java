package com.dacky.repository;

import com.dacky.domain.Firm;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FirmRepository extends R2dbcRepository<Firm, Long> {
    @Query("SELECT * FROM firm OFFSET :offset LIMIT :limit")
    Flux<Firm> findPaging(int offset, int limit);

    Mono<Firm> findByNameAndActiveTrue(String name);
}
