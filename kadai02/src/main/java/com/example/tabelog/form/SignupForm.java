package com.example.tabelog.form;

<<<<<<< HEAD
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {

	@NotBlank(message = "名前を入力してください")
	private String name;

	@NotBlank(message = "フリガナを入力してください")
	private String furigana;

	@NotBlank(message = "郵便番号を入力してください")
	private String postalCode;

	@NotBlank(message = "住所を入力してください")
	private String address;

	@NotBlank(message = "電話番号を入力してください")
	private String phoneNumber;

	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "有効なメールアドレスを入力してください")
	private String email;

	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 8, max = 255, message = "パスワードは8文字以上255文字以内で入力してください")
	private String password;

	@NotBlank(message = "パスワード（確認）を入力してください")
	private String passwordConfirmation;

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
=======
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupForm {
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
	@Email(message = "メールアドレスは正しい形式で入力してください。")
	private String email;

	@NotBlank(message = "パスワードを入力してください。")
	@Length(min = 8, message = "パスワードは8文字以上で入力してください。")
	private String password;

	@NotBlank(message = "パスワード（確認用）を入力してください。")
	private String passwordConfirmation;
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
}
