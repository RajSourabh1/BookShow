package com.example.BookMyShow.Service;

import com.example.BookMyShow.Convertor.TheaterConvertor;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.Models.Show;
import com.example.BookMyShow.Models.Theater;
import com.example.BookMyShow.Models.TheaterSeat;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.Repository.TheaterSeatRepository;
import com.example.BookMyShow.RequestResponseDTO.ShowResponseDTO;
import com.example.BookMyShow.RequestResponseDTO.TheaterDTO;
import com.example.BookMyShow.RequestResponseDTO.TheaterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    TheaterSeatRepository theaterSeatRepository;
    MovieRepository movieRepository;
    public String addTheater(TheaterDTO theaterDTO){

        Theater theater = TheaterConvertor.DtoToEntity(theaterDTO);
        List<TheaterSeat> theaterSeatList = createTheaterSeats();
        theater.setTheaterSeats(theaterSeatList);

        // for each TheaterSeat : we need to set the Theater
        for(TheaterSeat theaterSeat : theaterSeatList){
            theaterSeat.setTheater(theater);
        }
        theater.setTheaterSeats(theaterSeatList);
        theaterRepository.save(theater);
        return "Theater added successfully";
    }

    private List<TheaterSeat> createTheaterSeats(){
        List<TheaterSeat> seats = new ArrayList<>();
//        TheaterSeat theaterSeat1 = new TheaterSeat("1A", SeatType.Normal,100);
//        TheaterSeat theaterSeat2 = new TheaterSeat("1B", SeatType.Normal,100);
//        TheaterSeat theaterSeat3 = new TheaterSeat("1C", SeatType.Normal,100);
//        TheaterSeat theaterSeat4 = new TheaterSeat("1D", SeatType.Normal,100);
//        TheaterSeat theaterSeat5 = new TheaterSeat("1E", SeatType.Normal,100);
//
//        TheaterSeat theaterSeat6 = new TheaterSeat("2A", SeatType.VIP,200);
//        TheaterSeat theaterSeat7 = new TheaterSeat("2B", SeatType.VIP,200);
//        TheaterSeat theaterSeat8 = new TheaterSeat("2C", SeatType.VIP,200);
//        TheaterSeat theaterSeat9 = new TheaterSeat("2D", SeatType.VIP,200);
//        TheaterSeat theaterSeat10 = new TheaterSeat("2E", SeatType.VIP,200);
//
//        seats.add(theaterSeat1);
//        seats.add(theaterSeat2);
//        seats.add(theaterSeat3);
//        seats.add(theaterSeat4);
//        seats.add(theaterSeat5);
//        seats.add(theaterSeat6);
//        seats.add(theaterSeat7);
//        seats.add(theaterSeat8);
//        seats.add(theaterSeat9);
//        seats.add(theaterSeat10);

        for(int i=0;i<5;i++){
            char ch = (char)('A'+i);
            String seatNo = "1"+ch;
            TheaterSeat theaterSeat = new TheaterSeat(seatNo,SeatType.Normal,100);
            seats.add(theaterSeat);
        }

        for(int i=0;i<5;i++){
            char ch = (char)('A'+i);
            String seatNo = "2"+ch;
            TheaterSeat theaterSeat = new TheaterSeat(seatNo,SeatType.VIP,200);
            seats.add(theaterSeat);
        }
        for(TheaterSeat theaterSeat:seats){
            System.out.printf(theaterSeat.getSeatNo()+" ");
        }
       // theaterSeatRepository.saveAll(seats);
        return seats;
    }

    public List<TheaterResponse> getTheaters(String movieName){
        Movie movie = movieRepository.findByMovieName(movieName);
        List<Theater> Theaters = theaterRepository.findAll();
        List<TheaterResponse> result = null;
        List<Show> shows = null;

        for(Theater theater:Theaters){
            shows = theater.getShowList();

            for(Show show : shows){

                if(show.getMovie() == movie){
                    TheaterResponse theaterResponse = TheaterResponse.builder()
                            .theaterName(show.getTheater().getName())
                            .city(show.getTheater().getCity())
                            .address(show.getTheater().getAddress()).build();

                    result.add(theaterResponse);
                }
                break;
            }
        }
        return result;
    }
}
