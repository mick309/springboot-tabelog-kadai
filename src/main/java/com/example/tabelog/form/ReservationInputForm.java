package com.example.tabelog.form;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {

    @NotNull(message = "店舗IDは必須です。")
    private Long shopId;

    @NotNull(message = "ユーザーIDは必須です。")
    private Long userId;

    @NotNull(message = "予約日は必須です。")
    @FutureOrPresent(message = "予約日は本日以降の日付を指定してください。")
    private LocalDate reservationsDate;

    @NotNull(message = "予約時間は必須です。")
    private LocalTime reservationTime;

    @NotNull(message = "予約人数は必須です。")
    @Min(value = 1, message = "予約人数は1人以上を指定してください。")
    private Integer numberOfPeople;

    // 開店時間と閉店時間（バリデーション用）
    public static LocalTime getOpenTime() {
        return LocalTime.of(9, 0); // 例: 9時開店
    }

    public static LocalTime getCloseTime() {
        return LocalTime.of(22, 0); // 例: 22時閉店
    }
}
