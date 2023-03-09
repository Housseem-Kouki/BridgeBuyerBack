package com.example.demandeachatservice.Security;


import com.example.demandeachatservice.Entities.User;

public interface IUserService {

    public User laodUserByUserName(String email);
}
