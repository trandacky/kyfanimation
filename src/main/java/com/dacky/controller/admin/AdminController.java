package com.dacky.controller.admin;

import com.dacky.constant.KeyValueConstants;
import com.dacky.constant.MessageConstant;
import com.dacky.controller.BaseResponse;
import com.dacky.security.AuthoritiesConstants;
import com.dacky.service.appservice.KeyValueService;
import com.dacky.service.dto.KeyValueDTO;
import com.dacky.service.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private KeyValueService keyValueService;

    @GetMapping("/notification")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<BaseResponse> getNotification() {
        return keyValueService
            .getPublicNotification(
                Arrays.asList(
                    KeyValueConstants.PUBLIC_NOTIFICATION,
                    KeyValueConstants.PUBLIC_NOTIFICATION_IMAGE,
                    KeyValueConstants.NOTIFICATION,
                    KeyValueConstants.NOTIFICATION_IMAGE
                )
            )
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

    @PutMapping("/notification")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<BaseResponse> updateNotification(@RequestBody NotificationDTO notificationDTO) {
        try {
            return keyValueService
                .updateKeyValue(notificationDTO.getNotification(), KeyValueConstants.NOTIFICATION)
                .and(keyValueService.updateKeyValue(notificationDTO.getPublicNotificationImage(), KeyValueConstants.PUBLIC_NOTIFICATION_IMAGE))
                .and(keyValueService.updateKeyValue(notificationDTO.getNotificationImage(), KeyValueConstants.NOTIFICATION_IMAGE))
                .and(keyValueService.updateKeyValue(notificationDTO.getPublicNotification(), KeyValueConstants.PUBLIC_NOTIFICATION))
                .thenReturn(new BaseResponse(200, MessageConstant.MESSAGE_UPDATE_SUCCESS, null));
        } catch (Exception e) {
            return Mono.just(new BaseResponse(400, MessageConstant.MESSAGE_UPDATE_FAIL, null));
        }
    }
}
