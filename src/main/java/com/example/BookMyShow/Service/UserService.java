package com.example.BookMyShow.Service;

import com.example.BookMyShow.Convertor.UserConvertor;
import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.RequestResponseDTO.UserDTO;
import com.example.BookMyShow.RequestResponseDTO.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserDTO userDTO){
        User user = UserConvertor.UserDtoToEntity(userDTO);
        try{
            userRepository.save(user) ;
        }catch (Exception e){
            return "user couldn't be added";
        }
        return "Successfully added";
    }

    public UserResponse findUser(String name){
        User user = userRepository.findByName(name);
        UserResponse userResponse = new UserResponse(user.getName(),user.getMobile());
        return userResponse;
    }

    public List<UserResponse> allUsers(){
       List<User> users = userRepository.findAll();
       List<UserResponse> userList = new ArrayList<>();
       for(User user : users){
            UserResponse userResponse = UserResponse.builder()
                    .name(user.getName())
                    .mobile(user.getMobile()).build();
            userList.add(userResponse);
       }
       return  userList;
    }
}
