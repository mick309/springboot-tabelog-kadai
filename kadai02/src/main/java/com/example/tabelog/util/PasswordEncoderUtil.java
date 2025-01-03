package com.example.tabelog.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password"; // ここにエンコードしたい平文のパスワードを入力
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("エンコード済みパスワード: " + encodedPassword);
    }
}
