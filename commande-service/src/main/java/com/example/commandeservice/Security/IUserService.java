package com.example.commandeservice.Security;


import com.example.commandeservice.Entities.User;

public interface IUserService {

    public User laodUserByUserName(String email);
}
