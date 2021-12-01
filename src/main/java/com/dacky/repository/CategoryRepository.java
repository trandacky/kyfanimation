package com.dacky.repository;

import com.dacky.domain.Category;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CategoryRepository extends R2dbcRepository<Category, Long> {
    @Query("SELECT * FROM category OFFSET :offset LIMIT :limit")
    Flux<Category> findPaging(int offset, int limit);
}
