package com.ohouse.web.entity;

import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor


public class User {
    private String email;
    private String password;
    private String nickname;
    private int phone;
    private String birthday;

    @Builder
    public User(String email,String password,String nickname, int phone, String birthday){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.birthday = birthday;
    }


}
