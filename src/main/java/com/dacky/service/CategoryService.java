package com.dacky.service;

import com.dacky.domain.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Flux<Category> getAllCategoryPaging(int i, int limit);

    Mono<Long> rowCount();

    Flux<Category> getAllCategory();

    Mono<Category> updateCategory(Category category);

    Mono<Category> saveNewCategory(Category category);
}
