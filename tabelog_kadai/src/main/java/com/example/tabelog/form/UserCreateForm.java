package com.example.tabelog.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateForm {
	@NotBlank(message = "氏名は必須です")
	private String name;

	@NotBlank(message = "フリガナは必須です")
	@Pattern(regexp = "^[ァ-ヶー\\s]+$", message = "フリガナは全角カタカナで入力してください")
	private String furigana;

	@NotBlank(message = "郵便番号は必須です")
	@Pattern(regexp = "^\\d{3}-\\d{4}$", message = "郵便番号は「xxx-xxxx」の形式で入力してください")
	private String postalCode;

	@NotBlank(message = "住所は必須です")
	private String address;

	@NotBlank(message = "電話番号は必須です")
	@Pattern(regexp = "^0\\d{1,4}-\\d{1,4}-\\d{4}$", message = "電話番号は「0xx-xxxx-xxxx」の形式で入力してください")
	private String phoneNumber;

	@Email(message = "無効なメールアドレスです")
	@NotBlank(message = "メールアドレスは必須です")
	private String email;

	@NotBlank(message = "パスワードは必須です")
	@Size(min = 6, message = "パスワードは6文字以上で入力してください")
	private String password;

	@NotBlank(message = "パスワード（確認）は必須です")
	private String passwordConfirmation;
}
