package com.dacky.service.implement;

import com.dacky.repository.KeyValueDataRepository;
import com.dacky.service.appservice.KeyValueService;
import com.dacky.service.dto.KeyValueDTO;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class KeyValueImpl implements KeyValueService {

    @Autowired
    private KeyValueDataRepository keyValueDataRepository;

    @Override
    public Mono<KeyValueDTO> getNotification(String key) {
        return keyValueDataRepository
            .findOneByKey(key)
            .switchIfEmpty(null)
            .flatMap(keyValueData -> {
                if (ObjectUtils.isNotEmpty(keyValueData)) return Mono.just(new KeyValueDTO(keyValueData));
                return null;
            });
    }

    @Override
    public Flux<KeyValueDTO> getPublicNotification(List keys) {
        return keyValueDataRepository.findAllByKeyIn(keys).map(KeyValueDTO::new);
    }

    @Override
    public Mono<Void> updateKeyValue(String value, String key) {
        return keyValueDataRepository.updateKeyValue(value, key);
    }
}
