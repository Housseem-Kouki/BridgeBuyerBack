package com.example.emlacementservice.Security;


import com.example.emlacementservice.Entities.User;

public interface IUserService {

    public User laodUserByUserName(String email);
}
