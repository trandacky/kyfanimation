package com.dacky.service.dto;

import com.dacky.constant.RegexConstant;
import com.dacky.domain.User;
import java.time.Instant;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class UserInfoDTO {

    private String id;

    @Pattern(regexp = RegexConstant.VIETNAM_REGEX)
    private String firstName;

    @Pattern(regexp = RegexConstant.VIETNAM_REGEX)
    private String lastName;

    private String login;
    private String imageUrl;

    @Length(max = 4096)
    private String introduce;

    private Instant birthday;

    @Length(max = 15)
    private String phoneNumber;

    private String bgUrl;

    public UserInfoDTO() {}

    public UserInfoDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getLogin();
        this.imageUrl = user.getImageUrl();
        this.introduce = user.getIntroduce();
        this.birthday = user.getBirthday();
        this.phoneNumber = user.getPhoneNumber();
        this.bgUrl = user.getBgUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBgUrl() {
        return bgUrl;
    }

    public void setBgUrl(String bgUrl) {
        this.bgUrl = bgUrl;
    }
}
