package com.example.userservice.Services.User;


import com.example.userservice.Entities.User;

import java.util.List;

public interface IUserService {
    public User addUser(User u);
    public User updateUser(User u);
    public void deleteUser(int id);
    public User getUserById(int id);
    public List<User> getAllUsers();
    public boolean desActiverCompteUser(int idUser);

    public User affecterUserRole(int idUser , int idRole);
    public User laodUserByUserName(String email);
}
