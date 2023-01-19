package com.example.BookMyShow.Convertor;

import com.example.BookMyShow.Models.Theater;
import com.example.BookMyShow.RequestResponseDTO.TheaterDTO;

public class TheaterConvertor {

    public static Theater DtoToEntity(TheaterDTO theaterDTO){
        Theater theater = Theater.builder()
                .name(theaterDTO.getName())
                .city(theaterDTO.getCity())
                .address(theaterDTO.getAddress()).build();

        return theater;
    }
}
