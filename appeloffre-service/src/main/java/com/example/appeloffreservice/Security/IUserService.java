package com.example.appeloffreservice.Security;


import com.example.appeloffreservice.Entities.User;

public interface IUserService {

    public User laodUserByUserName(String email);
}
