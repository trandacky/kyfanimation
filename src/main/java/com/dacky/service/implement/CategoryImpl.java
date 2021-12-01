package com.dacky.service.implement;

import com.dacky.domain.Category;
import com.dacky.repository.CategoryRepository;
import com.dacky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Flux<Category> getAllCategoryPaging(int offset, int limit) {
        return categoryRepository.findPaging(offset, limit);
    }

    @Override
    public Mono<Long> rowCount() {
        return categoryRepository.count();
    }

    @Override
    public Flux<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> updateCategory(Category category) {
        return categoryRepository.findById(category.getId()).flatMap(category1 -> {
                category1.setReleaseTime(category.getReleaseTime());
                category1.setImageUrl(category.getImageUrl());
                category1.setName(category.getName());
                category1.setActive(category.isActive());
                return categoryRepository.save(category1);
            }
        );
    }

    @Override
    public Mono<Category> saveNewCategory(Category category) {
        Category category1 = new Category();
        category1.setReleaseTime(category.getReleaseTime());
        category1.setImageUrl(category.getImageUrl());
        category1.setName(category.getName());
        category1.setActive(category.isActive());
        return categoryRepository.save(category1);
    }
}
