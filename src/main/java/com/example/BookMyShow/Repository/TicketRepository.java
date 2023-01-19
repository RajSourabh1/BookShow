package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Models.Show;
import com.example.BookMyShow.Models.ShowSeats;
import com.example.BookMyShow.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

   // @Query("select * from shows s where s.id in (select s.id from ticket t where t.id =: ticketId)")
    //Show findShowByTicket(int ticketId);

   // @Query("select alloted_seats from ticket t where t.id =:ticketId")
   // List<ShowSeats> findSeatsFromTicket(int ticketId);
}
