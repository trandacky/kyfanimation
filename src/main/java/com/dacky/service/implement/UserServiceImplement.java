package com.dacky.service.implement;

import com.dacky.repository.UserRepository;
import com.dacky.service.appservice.UserService;
import com.dacky.service.dto.UserInfoDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserInfoDTO> getUserInfo(String login) {
        return userRepository.findOneByLogin(login).flatMap(user -> Mono.just(new UserInfoDTO(user)));
    }

    @Override
    public Mono<UserInfoDTO> updateUserInfo(UserInfoDTO userInfoDTO) {
        return userRepository
            .findOneByLogin(userInfoDTO.getLogin())
            .flatMap(user -> {
                String introduce = "wibu";
                String phoneNumber = "";
                if (StringUtils.isNotEmpty(userInfoDTO.getIntroduce())) {
                    user.setIntroduce(userInfoDTO.getIntroduce());
                } else user.setIntroduce(introduce);
                if (ObjectUtils.isNotEmpty(userInfoDTO.getBirthday())) {
                    user.setBirthday(userInfoDTO.getBirthday());
                }
                if (ObjectUtils.isNotEmpty(userInfoDTO.getPhoneNumber())) {
                    user.setPhoneNumber(userInfoDTO.getPhoneNumber());
                } else {
                    user.setPhoneNumber(phoneNumber);
                }
                user.setFirstName(userInfoDTO.getFirstName());
                user.setLastName(userInfoDTO.getLastName());
                return userRepository.save(user).flatMap(user1 -> Mono.just(new UserInfoDTO(user1)));
            });
    }
}
