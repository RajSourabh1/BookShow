package com.example.BookMyShow.RequestResponseDTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowDTO {

    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;

    private int theaterId;

    private double multiplier;
}
