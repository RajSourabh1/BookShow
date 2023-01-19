package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.Models.Theater;
import com.example.BookMyShow.RequestResponseDTO.TheaterDTO;
import com.example.BookMyShow.RequestResponseDTO.TheaterResponse;
import com.example.BookMyShow.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    private String createTheater(@RequestBody TheaterDTO theaterDTO){
        return theaterService.addTheater(theaterDTO);
    }

    @GetMapping("/getAllTheater")
    public List<TheaterResponse> getTheaters(@RequestParam("name")String movieName){
        return theaterService.getTheaters(movieName);
    }
}
