package com.example.tabelog.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@NotBlank(message = "氏名は必須です")
	private String name;

	@NotBlank(message = "フリガナは必須です")
	private String furigana;

	@NotBlank(message = "郵便番号は必須です")
	private String postalCode;

	@NotBlank(message = "住所は必須です")
	private String address;

	@NotBlank(message = "電話番号は必須です")
	private String phoneNumber;

	@NotBlank(message = "メールアドレスは必須です")
	private String email;

	@NotBlank(message = "パスワードは必須です")
	private String password;

	@NotBlank(message = "パスワード確認は必須です")
	private String passwordConfirmation;

	private Long  roleId; // null許容
}
