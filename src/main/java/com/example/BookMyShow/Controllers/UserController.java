package com.example.BookMyShow.Controllers;


import com.example.BookMyShow.RequestResponseDTO.UserDTO;
import com.example.BookMyShow.RequestResponseDTO.UserResponse;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @GetMapping("/findUser/{name}")
    public UserResponse findUser(@PathVariable("name")String userName){
      return userService.findUser(userName);
    }

    @GetMapping("/findAllUser")
    public List<UserResponse> findAllUsers(){
      return userService.allUsers();
    }
}
