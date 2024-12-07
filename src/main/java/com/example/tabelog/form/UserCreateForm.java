package com.example.tabelog.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCreateForm {

	@NotBlank(message = "氏名は必須です。")
	private String name;

	@NotBlank(message = "フリガナは必須です。")
	private String furigana;

	@NotBlank(message = "郵便番号は必須です。")
	private String postalCode;

	@NotBlank(message = "住所は必須です。")
	private String address;

	@NotBlank(message = "電話番号は必須です。")
	private String phoneNumber;

	@Email(message = "正しいメールアドレスを入力してください。")
	@NotBlank(message = "メールアドレスは必須です。")
	private String email;

	@NotBlank(message = "パスワードは必須です。")
	private String password;

	@NotBlank(message = "パスワード確認を入力してください。")
	private String passwordConfirmation; // パスワード確認用フィールド

	@NotNull(message = "ロールは必須です。")
	private Integer roleId; // ロールIDを追加
}
