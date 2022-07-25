package com.ohouse.web.dto;

import com.ohouse.web.entity.User;
import lombok.*;

@NoArgsConstructor
@Data
@Getter
@Setter
@ToString

public class UserDto {
    private String email;         //필수
    private String password;      //필수
    private String nickname;      //필수
    private int phone;            //option
    private String birthday;      //option

    public User toEntity(){
        User user = User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .birthday(birthday)
                .build();
        return user;
    }

        @Builder
        public UserDto(String email, String password, String nickname, int phone, String birthday) {

            this.email = email;
            this.password = password;
            this.nickname = nickname;
            this.phone = phone;
            this.birthday = birthday;

        }
    }

