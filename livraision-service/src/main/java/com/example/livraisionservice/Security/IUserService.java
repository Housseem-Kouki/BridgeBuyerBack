package com.example.livraisionservice.Security;


import com.example.livraisionservice.Entities.User;

public interface IUserService {

    public User laodUserByUserName(String email);
}
