package com.example.BookMyShow.Service;

import com.example.BookMyShow.Convertor.MovieConvertor;
import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.RequestResponseDTO.MovieDTO;
import com.example.BookMyShow.RequestResponseDTO.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieDTO movieDTO){
        Movie movie = MovieConvertor.DtoToEntity(movieDTO);
        try{
            movieRepository.save(movie);
        }catch(Exception e){
            return "movie not added";
        }
        return "Movie add successfully";
    }

    public MovieResponse findMovieByName(String name){
       Movie movie = movieRepository.findByMovieName(name);

       MovieResponse movieResponse = MovieResponse.builder()
               .movieName(movie.getMovieName())
               .releaseDate(movie.getReleaseDate())
               .duration(movie.getDuration()).build();
       return movieResponse;
    }
}
