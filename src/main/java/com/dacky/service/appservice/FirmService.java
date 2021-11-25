package com.dacky.service.appservice;

import com.dacky.controller.BaseResponse;
import com.dacky.domain.Firm;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FirmService {
    Flux<Firm> getAllFirm(int offset, int limit);
    Mono<Long> rowCount();
    Mono<Firm> saveNewFirm(Firm firm);

    Mono<Firm> updateFirm(Firm firm);
}
