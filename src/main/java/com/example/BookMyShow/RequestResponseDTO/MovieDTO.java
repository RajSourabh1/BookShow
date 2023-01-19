package com.example.BookMyShow.RequestResponseDTO;

import lombok.Data;

import java.util.Date;

@Data
public class MovieDTO {

   private String name;

   private Date releaseDate;

   private int duration;
}
