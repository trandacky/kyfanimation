package com.dacky.service.appservice;

import com.dacky.service.dto.UserInfoDTO;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserInfoDTO> getUserInfo(String login);

    Mono<UserInfoDTO> updateUserInfo(UserInfoDTO userInfoDTO);
}
