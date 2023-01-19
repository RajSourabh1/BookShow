package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Models.ShowSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeats,Integer> {
}
