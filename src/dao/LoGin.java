package dao;

import javabean.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface LoGin {

    /*
      1.校验用户是否存在;
     */
    abstract List<User> checkUser(String username, String password);


    /*
     2.校验用户类型;
     */
    abstract String checkUserType(List<User> users);


    /*
       3.制作登录后的seesion,在一定时间内,免登录;
     */
    abstract Cookie makeCookie(HttpServletRequest req, HttpSession session);
}
