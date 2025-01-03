package com.example.tabelog.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryEditForm {

		@NotNull
		private Integer id;

		@NotNull(message = "カテゴリを入力してください。")
		@Length(max = 50, message = "カテゴリは50文字以内で入力してください。")
		private String category;


}
