package com.example.BookMyShow.Service;

import com.example.BookMyShow.Models.Show;
import com.example.BookMyShow.Models.ShowSeats;
import com.example.BookMyShow.Models.Ticket;
import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.RequestResponseDTO.BookTicketDTO;
import com.example.BookMyShow.RequestResponseDTO.CancelTicketDTO;
import com.example.BookMyShow.RequestResponseDTO.UserTicketResponse;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;
    UserRepository userRepository;
    TicketRepository ticketRepository;
    public String createTicket(BookTicketDTO bookTicketDTO) throws Exception{
        List<String> requestedSeats = bookTicketDTO.getRequestedSeats();

        for(String str : requestedSeats){
            System.out.println(str);
        }

        Show show = showRepository.findById(bookTicketDTO.getShowId()).get();
        User user = userRepository.findById(bookTicketDTO.getUserId()).get();

        List<ShowSeats> showSeats = show.getShowSeats();
        // check whether the requested sets are available or not
        List<ShowSeats> bookedSeats = new ArrayList<>();
        for(ShowSeats showSeat:showSeats){
            String seatNo = showSeat.getSeatNo();
            System.out.println(seatNo+" ");
            if(showSeat.isBooked()==false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }
        if(bookedSeats.size()!=requestedSeats.size()){
            // some of the requested seats are not available
            throw new Exception("requested seats are not available");
        }
        // all the seats are available
        Ticket ticket = new Ticket();

        double totalAmt = 0;
        double multiplier = show.getMultiplier();
        int rate = 0;

        String allotedSeats = "";

        // calculating amount,booked status,
        for(ShowSeats bookedSeat:bookedSeats){
            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticket);
            bookedSeat.setShow(show);
            allotedSeats = allotedSeats + bookedSeat.getSeatNo()+",";

            if(bookedSeat.getSeatNo().charAt(0)=='1')
                rate = 100;
            else
                rate = 200;
            totalAmt = totalAmt+multiplier*rate;
        }
        ticket.setBookedAt(new Date());
        ticket.setAmt((int)totalAmt);
        ticket.setShow(show);
        ticket.setUser(user);
        ticket.setBookedSeats(bookedSeats);
        ticket.setAllotedSeats(allotedSeats);

        ticketRepository.save(ticket);
        return "successfully ticket booked";
    }

    public String cancelTicket(CancelTicketDTO cancelTicketDTO){
        User user = userRepository.findById(cancelTicketDTO.getUserId()).get();
        Ticket ticket = ticketRepository.findById(cancelTicketDTO.getTicketId()).get();
        int amt = ticket.getAmt();
        List<ShowSeats> bookedSeats = ticket.getBookedSeats();
        Show show = ticket.getShow();

        List<ShowSeats> showSeats = show.getShowSeats();
        for(ShowSeats showSeat:showSeats){
            String seatNo = showSeat.getSeatNo();
            if(showSeat.isBooked()==true && bookedSeats.contains(seatNo)){
                bookedSeats.remove(showSeat);
            }
        }

        int returnAmt = (int) (amt - (amt * 0.2));

        for(ShowSeats bookedSeat:bookedSeats){
            bookedSeat.setBooked(false);
            bookedSeat.setBookedAt(null);
            bookedSeat.setTicket(null);
            bookedSeat.setShow(null);
        }

        ticket.setBookedAt(null);
        ticket.setAmt(returnAmt);
        ticket.setShow(null);
       // ticket.setUser(null);
        ticket.setUser(user);
        ticket.setBookedSeats(null);
        ticket.setAllotedSeats(null);

        ticketRepository.save(ticket);
        return "successfully ticket canceled and amt is "+returnAmt;
    }

    public List<UserTicketResponse> findTickets(int userId){
        User user = userRepository.findById(userId).get();
        List<Ticket> tickets = user.getTickets();
        List<UserTicketResponse> result = null;

        for(Ticket ticket:tickets){
            UserTicketResponse userTicketResponse = UserTicketResponse.builder()
                    .movieName(ticket.getShow().getMovie().getMovieName())
                    .theaterName(ticket.getShow().getTheater().getName())
                    .seats(ticket.getAllotedSeats())
                    .time(ticket.getShow().getShowTime()).build();
            result.add(userTicketResponse);
        }
        return result;
    }
}
