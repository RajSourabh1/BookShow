package com.example.BookMyShow.RequestResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    private String name;

    private String mobile;
}
