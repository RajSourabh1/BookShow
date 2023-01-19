package com.example.BookMyShow.Convertor;


import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.RequestResponseDTO.MovieDTO;

public class MovieConvertor {

    public static Movie DtoToEntity(MovieDTO movieDTO){
        Movie movie = Movie.builder()
                .movieName(movieDTO.getName())
                .releaseDate(movieDTO.getReleaseDate())
                .duration(movieDTO.getDuration()).build();

        return movie;
    }
}
