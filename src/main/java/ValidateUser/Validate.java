package ValidateUser;
import Model.Admin_cred;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import Encryption.Encrypt;
import Model.User;
public class Validate {
	
	public static Boolean isAdmin(HttpServletRequest request,HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        Admin_cred admin = (Admin_cred) session.getAttribute("admin");
        if(admin == null)
        {
            return false;
        }
        if (cookies != null) {
            System.out.println(cookies[0]);
            for (Cookie c : cookies) {

                if (c.getName().equals("isAdmin")) {
                    if (Encrypt.decrypt(c.getValue()).equals(admin.getAdmin_name())) {
                       return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
    public static Boolean isLogin(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null)
        {
            return false;
        }
        if (cookies != null) {
            System.out.println(cookies[0]);
            for (Cookie c : cookies) {

                if (c.getName().equals("isLogin")) {
                    if (Encrypt.decrypt(c.getValue()).equals(user.getName())) {
                       return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
