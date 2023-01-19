package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.RequestResponseDTO.MovieDTO;
import com.example.BookMyShow.RequestResponseDTO.MovieResponse;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody MovieDTO movieDTO){
        return movieService.addMovie(movieDTO);
    }

    @GetMapping("/findMovie/{name}")
    public MovieResponse findMovie(@PathVariable("name")String movieName){
        return movieService.findMovieByName(movieName);
    }
}
