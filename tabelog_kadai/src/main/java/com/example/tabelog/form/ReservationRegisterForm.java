package com.example.tabelog.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
	private Integer shopId;
    
    private Integer userId;    
    
    private String ReservationDate;
    
    private String ReservationTime;
    
    private Integer numberOfPeople;
}
