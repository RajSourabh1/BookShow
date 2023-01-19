package com.example.BookMyShow.Convertor;

import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.RequestResponseDTO.UserDTO;

public class UserConvertor {

    public static User UserDtoToEntity(UserDTO userDTO){
        User user = User.builder()
                .name(userDTO.getName())
                .mobile(userDTO.getMobile()).build();
        return user;
    }
}
