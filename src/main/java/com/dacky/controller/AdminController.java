package com.dacky.controller;

import com.dacky.constant.FirmConstant;
import com.dacky.constant.KeyValueConstants;
import com.dacky.constant.MessageConstant;
import com.dacky.domain.Firm;
import com.dacky.security.AuthoritiesConstants;
import com.dacky.service.appservice.FirmService;
import com.dacky.service.appservice.KeyValueService;
import com.dacky.service.dto.KeyValueDTO;
import com.dacky.service.dto.NotificationDTO;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private KeyValueService keyValueService;

    @Autowired
    private FirmService firmService;

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
        return keyValueService
            .updateKeyValue(notificationDTO.getNotification(), KeyValueConstants.NOTIFICATION)
            .and(keyValueService.updateKeyValue(notificationDTO.getPublicNotificationImage(), KeyValueConstants.PUBLIC_NOTIFICATION_IMAGE))
            .and(keyValueService.updateKeyValue(notificationDTO.getNotificationImage(), KeyValueConstants.NOTIFICATION_IMAGE))
            .and(keyValueService.updateKeyValue(notificationDTO.getPublicNotification(), KeyValueConstants.PUBLIC_NOTIFICATION))
            .thenReturn(new BaseResponse(200, MessageConstant.MESSAGE_UPDATE_SUCCESS, null));
    }

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

    @GetMapping("/firm/new")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<BaseResponse> addNewFirm(@RequestBody Firm firm) {
        return firmService.saveNewFirm(firm).flatMap(firm1 -> Mono.just(new BaseResponse(200, MessageConstant.MESSAGE_SUCCESS, firm1)));
    }

    @GetMapping("/firm/update")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public Mono<BaseResponse> updateFirm(@RequestBody Firm firm) {
        return firmService.updateFirm(firm).flatMap(firm1 -> Mono.just(new BaseResponse(200, MessageConstant.MESSAGE_SUCCESS, firm1)));
    }
}
