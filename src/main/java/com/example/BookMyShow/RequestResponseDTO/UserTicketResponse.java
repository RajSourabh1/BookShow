package com.example.BookMyShow.RequestResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserTicketResponse {
    private String movieName;

    private String theaterName;

    private String seats;

    private LocalTime time;
}
