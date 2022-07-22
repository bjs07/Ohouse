package com.ohouse.web.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class User {
    private String email;         //필수
    private String password;      //필수
    private String nickname;      //필수
    private int phone;            //option
    private String birthday;      //option

    private User() {

    }

    public User(String email, String password, String nickname) {

        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static class Builder {

        private final String email;         //필수
        private final String password;      //필수
        private final String nickname;      //필수
        private int phone;
        private String birthday;

        public Builder(String email, String password, String nickname) {
            this.email = email;
            this.password = password;
            this.nickname = nickname;
        }

        public Builder setPhone(int phone) {
            this.phone = phone;
            return this;

        }

        public Builder setBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public User build() {
            User user = new User();
            user.email = email;
            user.password = password;
            user.nickname = nickname;
            user.phone = phone;
            user.birthday = birthday;

            return user;

        }
    }
}
