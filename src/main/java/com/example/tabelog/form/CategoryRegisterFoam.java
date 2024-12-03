package com.example.tabelog.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRegisterFoam {
	@NotNull(message = "カテゴリを入力してください。")
	@Length(max = 50, message = "カテゴリは50文字以内で入力してください。")
	private String category;


}
