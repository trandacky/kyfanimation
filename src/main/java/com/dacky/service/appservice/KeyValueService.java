package com.dacky.service.appservice;

import com.dacky.service.dto.KeyValueDTO;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface KeyValueService {
    Mono<KeyValueDTO> getNotification(String key);
    Flux<KeyValueDTO> getPublicNotification(List keys);
    Mono<Void> updateKeyValue(String value, String key);
}
