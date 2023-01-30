package com.example.BookMyShow.Service;

import com.example.BookMyShow.Models.*;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.ShowSeatRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.RequestResponseDTO.ShowDTO;
import com.example.BookMyShow.RequestResponseDTO.ShowResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;
    ShowRepository showRepository;

    public String createShow(ShowDTO showDTO){

        Show show = Show.builder()
                                  .showDate(showDTO.getShowDate())
                                  .showTime(showDTO.getShowTime())
                                  .multiplier(showDTO.getMultiplier()).build();

        Movie movie = movieRepository.findByMovieName(showDTO.getMovieName());
        Theater theater = theaterRepository.findById(showDTO.getTheaterId()).get();

        show.setMovie(movie);
        show.setTheater(theater);

        //because we are doing bidirectional mapping we have to set show in movie and theater entity also
        movie.getShows().add(show);
        theater.getShowList().add(show);

        List<ShowSeats> showSeatsList = createShowSeats(theater.getTheaterSeats());
        show.setShowSeats(showSeatsList);
        for(ShowSeats showSeats : showSeatsList){
            showSeats.setShow(show);
        }

        movieRepository.save(movie);
        theaterRepository.save(theater);
        //showRepository.save(show); because its parent movie is being called
        return "Show created successfully";
    }

    public List<ShowSeats> createShowSeats(List<TheaterSeat> theaterSeatList){
        List<ShowSeats> seats = new ArrayList<>();
        for (TheaterSeat theaterSeat : theaterSeatList){
            ShowSeats showSeats = ShowSeats.builder().seatNo(theaterSeat.getSeatNo()).seatType(theaterSeat.getSeatType()).build();

            seats.add(showSeats);
        }
        showSeatRepository.saveAll(seats);
        return seats;
    }

    public List<ShowResponseDTO> getShows(int movieId, LocalDate date, LocalTime fromTime, LocalTime toTime) throws ParseException {
        List<ShowResponseDTO> showList = new ArrayList<>();
        Movie movie = movieRepository.findById(movieId).get();
        List<Show> shows = movie.getShows();
        for(Show show:shows){

           // Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(String.valueOf(date));

            int value1 = fromTime.compareTo(show.getShowTime());
            int value2 = toTime.compareTo(show.getShowTime());

            if(show.getShowDate().compareTo(date)==0 && value1<=0 && value2>=0){
                ShowResponseDTO showResponseDTO = ShowResponseDTO.builder()
                                                                           .movieName(show.getMovie().getMovieName())
                                                                           .theaterName(show.getTheater().getName())
                                                                           .date(show.getShowDate())
                                                                           .time(show.getShowTime()).build();

                showList.add(showResponseDTO);
            }
        }
        return showList;
    }
}
