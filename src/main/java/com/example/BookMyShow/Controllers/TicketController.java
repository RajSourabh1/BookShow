package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.Models.Ticket;
import com.example.BookMyShow.RequestResponseDTO.BookTicketDTO;
import com.example.BookMyShow.RequestResponseDTO.CancelTicketDTO;
import com.example.BookMyShow.RequestResponseDTO.UserTicketResponse;
import com.example.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/add")
    public String bookTicket(@RequestBody BookTicketDTO bookTicketDTO){
        try{
           return ticketService.createTicket(bookTicketDTO);
        }catch(Exception e){
           return "not available";
        }
    }

    @PostMapping("/cancel")
    public String cancelTicket(@RequestBody CancelTicketDTO cancelTicketDTO){
        return ticketService.cancelTicket(cancelTicketDTO);
    }

    @GetMapping("/getTickets")
    public List<UserTicketResponse> getTicket(@RequestParam("id")int id){
        return ticketService.findTickets(id);
    }
}
