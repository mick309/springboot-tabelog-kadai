package com.example.tabelog.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserEditForm {

	@NotNull(message = "IDは必須です。")
	private Integer id;

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
	private String email;

	// パスワードは空でも許容する場合、ここでは@NotBlankを削除可能
	private String password;

	// 引数なしコンストラクター（デフォルトで必要）
	public UserEditForm() {
	}

	// 必要なフィールドを初期化するコンストラクター
	public UserEditForm(Integer id, String name, String furigana, String postalCode,
			String address, String phoneNumber, String email) {
		this.id = id;
		this.name = name;
		this.furigana = furigana;
		this.postalCode = postalCode;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = ""; // パスワードは空文字で初期化
	}
}
