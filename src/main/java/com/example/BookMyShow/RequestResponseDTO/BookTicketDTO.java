package com.example.BookMyShow.RequestResponseDTO;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketDTO {

    private List<String> requestedSeats;

    private int showId;

    private int userId;
}
