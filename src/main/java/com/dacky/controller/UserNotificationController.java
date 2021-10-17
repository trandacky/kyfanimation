package com.dacky.controller;

import com.dacky.constant.KeyValueConstants;
import com.dacky.constant.MessageConstant;
import com.dacky.security.AuthoritiesConstants;
import com.dacky.service.appservice.KeyValueService;
import com.dacky.service.dto.KeyValueDTO;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserNotificationController {

    @Autowired
    private KeyValueService keyValueService;

    @GetMapping("/notification")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public Mono<BaseResponse> getNotification() {
        return keyValueService
            .getPublicNotification(Arrays.asList(KeyValueConstants.NOTIFICATION, KeyValueConstants.NOTIFICATION_IMAGE))
            .collectList()
            .map(keyValueDTOS -> {
                Map<String, Object> notification = new HashMap<>();
                for (KeyValueDTO keyValueDTO : keyValueDTOS) {
                    notification.put(keyValueDTO.getKey(), keyValueDTO);
                }
                return notification;
            })
            .flatMap(o -> Mono.just(new BaseResponse(200, MessageConstant.MESSAGE_SUCCESS, o)));
    }
}
