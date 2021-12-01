package com.dacky.controller.admin;

import com.dacky.constant.FirmConstant;
import com.dacky.constant.MessageConstant;
import com.dacky.controller.BaseResponse;
import com.dacky.controller.PagingResponse;
import com.dacky.domain.Category;
import com.dacky.domain.Firm;
import com.dacky.security.AuthoritiesConstants;
import com.dacky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{page}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<PagingResponse> getAllCategoryPaging(@PathVariable int page) {
        return categoryService
            .getAllCategoryPaging(page * FirmConstant.LIMIT, FirmConstant.LIMIT)
            .collectList()
            .flatMap(o ->
                categoryService.rowCount().flatMap(aLong -> Mono.just(new PagingResponse(200, MessageConstant.MESSAGE_SUCCESS, o, aLong)))
            );
    }

    @GetMapping("/category")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<PagingResponse> getAllCategory() {
        return categoryService
            .getAllCategory()
            .collectList()
            .flatMap(o ->
                categoryService.rowCount().flatMap(aLong -> Mono.just(new PagingResponse(200, MessageConstant.MESSAGE_SUCCESS, o, aLong)))
            );
    }

    @PostMapping("/category/new")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<BaseResponse> addNewFirm(@RequestBody Category category) {
        try {
            return categoryService.saveNewCategory(category).flatMap(firm1 -> Mono.just(new BaseResponse(200, MessageConstant.MESSAGE_SUCCESS, null)));
        } catch (Exception e) {
            return Mono.just(new BaseResponse(400, MessageConstant.MESSAGE_FAIL, null));
        }
    }

    @PutMapping("/category/update")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<BaseResponse> updateFirm(@RequestBody Category category) {
        try {
            return categoryService.updateCategory(category).flatMap(firm1 -> Mono.just(new BaseResponse(200, MessageConstant.MESSAGE_SUCCESS, null)));
        } catch (Exception e) {
            return Mono.just(new BaseResponse(400, MessageConstant.MESSAGE_UPDATE_FAIL, null));
        }
    }
}
