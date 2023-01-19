package com.example.BookMyShow.RequestResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponseDTO {

    private String movieName;

    private String theaterName;

    private LocalDate date;

    private LocalTime time;
}
