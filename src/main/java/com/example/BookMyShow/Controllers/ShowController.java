package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.Models.Show;
import com.example.BookMyShow.RequestResponseDTO.ShowDTO;
import com.example.BookMyShow.RequestResponseDTO.ShowResponseDTO;
import com.example.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public String addShow(@RequestBody ShowDTO showDTO){
        return showService.createShow(showDTO);
    }

    @GetMapping("/getAllShows")
    public List<ShowResponseDTO> getShow(@RequestParam("id") int movieId,
                                         @RequestParam("showDate") Date date,
                                         @RequestParam("fromTime")LocalTime fromTime,
                                         @RequestParam("toTime")LocalTime toTime){
        return showService.getShows(movieId,date,fromTime,toTime);
    }
}
