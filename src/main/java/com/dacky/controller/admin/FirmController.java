package com.dacky.controller.admin;

import com.dacky.constant.FirmConstant;
import com.dacky.constant.MessageConstant;
import com.dacky.controller.BaseResponse;
import com.dacky.controller.PagingResponse;
import com.dacky.domain.Firm;
import com.dacky.security.AuthoritiesConstants;
import com.dacky.service.appservice.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin")
public class FirmController {

    @Autowired
    private FirmService firmService;

    @GetMapping("/firm/{page}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<PagingResponse> getAllFirm(@PathVariable int page) {
        return firmService
            .getAllFirm(page * FirmConstant.LIMIT, FirmConstant.LIMIT)
            .collectList()
            .flatMap(o ->
                firmService.rowCount().flatMap(aLong -> Mono.just(new PagingResponse(200, MessageConstant.MESSAGE_SUCCESS, o, aLong)))
            );
    }

    @PostMapping("/firm/new")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<BaseResponse> addNewFirm(@RequestBody Firm firm) {
        try {
            return firmService.saveNewFirm(firm).flatMap(firm1 -> Mono.just(new BaseResponse(200, MessageConstant.MESSAGE_SUCCESS, firm1)));
        } catch (Exception e) {
            return Mono.just(new BaseResponse(400, MessageConstant.MESSAGE_FAIL, null));
        }

    }

    @PutMapping("/firm/update")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<BaseResponse> updateFirm(@RequestBody Firm firm) {
        try {
            return firmService.updateFirm(firm).flatMap(firm1 -> Mono.just(new BaseResponse(200, MessageConstant.MESSAGE_SUCCESS, null)));
        } catch (Exception e) {
            return Mono.just(new BaseResponse(400, MessageConstant.MESSAGE_UPDATE_FAIL, null));
        }

    }
}
