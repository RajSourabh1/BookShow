package com.example.BookMyShow.RequestResponseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterResponse {

    private String theaterName;

    private String city;

    private String address;
}
