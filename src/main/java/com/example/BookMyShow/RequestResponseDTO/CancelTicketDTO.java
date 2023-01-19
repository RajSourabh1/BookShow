package com.example.BookMyShow.RequestResponseDTO;

import lombok.Data;

@Data
public class CancelTicketDTO {
    private int userId;

    private int ticketId;

    private int showId;
}
