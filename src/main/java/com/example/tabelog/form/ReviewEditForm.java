package com.example.tabelog.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewEditForm {

	private Integer id;

	@NotNull(message = "評価を入力してください")
	private Integer evaluation;

	@NotBlank(message = "レビューコメントを入力してください")
	private String review_comment;

	// 引数なしのデフォルトコンストラクタは @Data によって自動生成されます
	public ReviewEditForm(Integer id, Integer evaluation, String review_comment) {
		this.id = id;
		this.evaluation = evaluation;
		this.review_comment = review_comment;
	}
}
