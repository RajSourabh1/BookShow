package com.example.BookMyShow.Models;


import com.example.BookMyShow.Enums.SeatType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_seat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private int rate;

    @ManyToOne
    @JoinColumn
    private Theater theater;

    public TheaterSeat(String seatNo, SeatType seatType, int rate) {
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.rate = rate;
    }
}
