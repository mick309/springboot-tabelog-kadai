package com.example.tabelog.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewEditForm {

    private Long id; // IDはLong型

    @NotNull(message = "評価を入力してください")
    private Integer evaluation;

    @NotBlank(message = "レビューコメントを入力してください")
    private String reviewComment;

    // コンストラクタ（Long型のidを受け入れる）
    public ReviewEditForm(Long id, Integer evaluation, String reviewComment) {
        this.id = id;
        this.evaluation = evaluation;
        this.reviewComment = reviewComment;
    }
}
