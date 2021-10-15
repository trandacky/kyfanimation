package com.dacky.controller;

import com.dacky.constant.MessageConstant;
import com.dacky.security.AuthoritiesConstants;
import com.dacky.security.SecurityUtils;
import com.dacky.service.appservice.UserService;
import com.dacky.service.dto.UserInfoDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {

    private final UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public Mono<BaseResponse> getUserInfo() {
        try {
            return SecurityUtils
                .getCurrentUserLogin()
                .flatMap(s ->
                    userService
                        .getUserInfo(s)
                        .flatMap(userInfoDTO -> Mono.just(new BaseResponse(200, MessageConstant.MESSAGE_SUCCESS, userInfoDTO)))
                );
        } catch (Exception E) {
            return Mono.just(new BaseResponse(400, MessageConstant.MESSAGE_FAIL, null));
        }
    }

    @PutMapping("/profile")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public Mono<BaseResponse> updateUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        return SecurityUtils
            .getCurrentUserLogin()
            .flatMap(s -> {
                if (userInfoDTO.getLogin().equals(s)) {
                    try {
                        return userService
                            .updateUserInfo(userInfoDTO)
                            .flatMap(dataUpdated -> Mono.just(new BaseResponse(200, MessageConstant.MESSAGE_UPDATE_SUCCESS, dataUpdated)));
                    } catch (Exception e) {
                        return Mono.just(new BaseResponse(400, MessageConstant.MESSAGE_UPDATE_FAIL, null));
                    }
                } else {
                    return Mono.just(new BaseResponse(403, MessageConstant.MESSAGE_DATA_INVALID, null));
                }
            });
    }
}
