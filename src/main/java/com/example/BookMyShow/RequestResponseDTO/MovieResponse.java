package com.example.BookMyShow.RequestResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class MovieResponse {

    private String movieName;

    private Date releaseDate;

    private int duration;
}
