package com.example.userservice.Services.User;


import com.example.userservice.Entities.Privilege;
import com.example.userservice.Entities.User;

import javax.mail.MessagingException;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

public interface IUserService {
    public User addUser(User u);
    public User updateUser(User u);
    public void deleteUser(int id);
    public User getUserById(int id);
    public List<User> getAllUsers();
    public boolean desActiverCompteUser(int idUser);

    public User affecterUserRole(int idUser , int idRole);
    public User laodUserByUserName(String email);


    User addUserWithRoleAndAffectPrivileges(User user);


    Response requestPasswordReset(String email) throws Exception;
    Response resetPassword(String token, String NewPassword , String ConfirmPassword);

    Set<Privilege> getListPrivilegesUser(int idUser);


    User annulerPrivilegeUser(int idUser , int idPrivilege);

    User addPrivilegeToUser(int idUser , int idPrivilegr);
}
