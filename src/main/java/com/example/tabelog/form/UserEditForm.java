package com.example.tabelog.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserEditForm {

    @NotNull(message = "IDは必須です。")
    private Long id;

    @NotBlank(message = "氏名を入力してください。")
    private String name;

    @NotBlank(message = "フリガナを入力してください。")
    private String furigana;

    @NotBlank(message = "郵便番号を入力してください。")
    private String postalCode;

    @NotBlank(message = "住所を入力してください。")
    private String address;

    @NotBlank(message = "電話番号を入力してください。")
    private String phoneNumber;

    @NotBlank(message = "メールアドレスを入力してください。")
    @Email(message = "有効なメールアドレスを入力してください。")
    private String email;

    private String password; // パスワードフィールドを追加
    private String passwordConfirmation; // 確認用パスワード

    @NotNull(message = "ロールIDは必須です。")
    private Long roleId;

    public UserEditForm(Long id, String name, String furigana, String postalCode,
                        String address, String phoneNumber, String email, Long roleId) {
        this.id = id;
        this.name = name;
        this.furigana = furigana;
        this.postalCode = postalCode;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roleId = roleId;
    }

    // パスワード関連のセッター・ゲッター
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}