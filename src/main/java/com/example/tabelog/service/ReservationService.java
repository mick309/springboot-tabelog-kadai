package com.example.tabelog.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.example.tabelog.entity.Reservation;
import com.example.tabelog.form.ReservationInputForm;
import com.example.tabelog.repository.ReservationRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

   
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // 予約日が有効か確認
    public boolean isReservationDateValid(LocalDate date) {
        return !date.isBefore(LocalDate.now());
    }

    // 予約時間が有効か確認
    public boolean isReservationTimeValid(LocalTime time) {
        return !(time.isBefore(ReservationInputForm.getOpenTime()) ||
                time.isAfter(ReservationInputForm.getCloseTime().minusHours(1)));
    }

    // IDによる予約検索
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID: " + id));
    }

    // 予約を保存する
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // 予約を削除する
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
