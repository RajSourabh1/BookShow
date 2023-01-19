package com.example.BookMyShow.Models;


import com.example.BookMyShow.Enums.SeatType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "show_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private boolean booked;

    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    private Ticket ticket;

    @ManyToOne
    @JoinColumn
    private Show show;
}
