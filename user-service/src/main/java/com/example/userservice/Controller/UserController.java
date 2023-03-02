package com.example.userservice.Controller;

import com.example.userservice.Entities.User;
import com.example.userservice.Services.User.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class UserController {
IUserService iUserService;
    @GetMapping("/hello")
    public String Hello(){

        return "bills hello";

    }

    @GetMapping("/AllUsers")
    @ResponseBody
    public List<User> getAllUsers(){
        return iUserService.getAllUsers();
    }


    @PostMapping("/addUser")
    @ResponseBody
    public User addUser (@RequestBody User user){
        return iUserService.addUser(user);
    }

    @GetMapping("/getUserById/{id}")
    @ResponseBody
    public User  getUserById(@PathVariable("id") int id){
        return   iUserService.getUserById(id);
    }


    @DeleteMapping("/deleteUser/{id}")
    private void deleteUser(@PathVariable("id") int id)
    {
        iUserService.deleteUser(id);
    }

    @PutMapping("/updateUser")
    private User updateUser(@RequestBody User user)
    {
        iUserService.updateUser(user);
        return user;
    }

    @PutMapping("/desActiverUser/{id}")
    private Boolean desActiverUser(@PathVariable("id") int id)
    {
        return iUserService.desActiverCompteUser(id);
    }

}
