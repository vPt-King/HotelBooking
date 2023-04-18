/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidateUser;

import DB.DBconnect;
import Encryption.Encrypt;
import Model.Admin_cred;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import Model.User;
/**
 *
 * @author TDT Computer
 */
public class UserCookie {
    public static void SetAdminCookie( HttpServletRequest request , HttpServletResponse response, Admin_cred admin)
    {
        Cookie c = new Cookie("isAdmin", Encrypt.encrypt(admin.getAdmin_name()));
        HttpSession session = request.getSession();
        Admin_cred adminCookie = new Admin_cred(admin.getId(),admin.getAdmin_name(),admin.getAdmin_pass());
        session.setMaxInactiveInterval(86400);
        session.setAttribute("admin", adminCookie);
        c.setMaxAge(86400);
        response.addCookie(c);
    }
    public static void SetUserCookie(HttpServletRequest request , HttpServletResponse response,User user) 
    {
        Cookie c = new Cookie("isLogin", Encrypt.encrypt(user.getName()));
        HttpSession session = request.getSession();
        User userCookie = new User(user.getName(),user.getPassword(), user.getIsAdmin());
        session.setMaxInactiveInterval(86400);
        session.setAttribute("user", userCookie);
        c.setMaxAge(86400);
        response.addCookie(c);
    }
}
