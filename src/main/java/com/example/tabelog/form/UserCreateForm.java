package com.example.tabelog.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateForm {

	@NotBlank(message = "名前は必須項目です。")
	@Size(max = 50, message = "名前は50文字以内で入力してください。")
	private String name;

	@NotBlank(message = "ふりがなは必須項目です。")
	@Size(max = 50, message = "ふりがなは50文字以内で入力してください。")
	private String furigana;

	@NotBlank(message = "郵便番号は必須項目です。")
	@Size(max = 10, message = "郵便番号は10文字以内で入力してください。")
	private String postalCode;

	@NotBlank(message = "住所は必須項目です。")
	@Size(max = 255, message = "住所は255文字以内で入力してください。")
	private String address;

	@NotBlank(message = "電話番号は必須項目です。")
	@Size(max = 15, message = "電話番号は15文字以内で入力してください。")
	private String phoneNumber;

	@NotBlank(message = "メールアドレスは必須項目です。")
	@Email(message = "有効なメールアドレスを入力してください。")
	private String email;

	@NotBlank(message = "パスワードは必須項目です。")
	@Size(min = 6, message = "パスワードは6文字以上で入力してください。")
	private String password;

	@NotBlank(message = "パスワード確認は必須項目です。")
	private String passwordConfirmation;

	// 新たにロールを追加
	@NotBlank(message = "ロールは必須項目です。")
	private String roleName;
}
