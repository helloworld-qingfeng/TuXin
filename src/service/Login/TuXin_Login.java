package service.Login;

import dao.LoGin;
import javabean.User;
import utils.DB.Driverutils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TuXin_Login implements LoGin {

    /*
    1.查询数据库,看看是否有对应的密码、用户，并且返回user集合；
     */
    @Override
    public  List<User> checkUser(String username, String password) {
        //获取数据库连接对象;
        Connection connection = Driverutils.GetConnection();

        //获取执行sql的对象,防止sql注入那个;
        String  sql = "SELECT * FROM `User` WHERE username = ? AND password= ?";
        PreparedStatement preparedStatement = Driverutils.GetprepareStatement(connection, sql);

        List<User> user = null;

//        设置占位符号;
        try {
            preparedStatement.setString(1,username);  //开始设置;
            preparedStatement.setString(2,password);  //开始设置;
            ResultSet resultSet = preparedStatement.executeQuery(); //开始查询;
            while (resultSet.next()){
                String db_password = resultSet.getString("password");
                String db_username = resultSet.getString("username");
                user = new ArrayList<>();
                user.add(new User().setUsername(db_username).setPassword(db_password));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /*
    1.根据user集合，用于配合登录使用,即判断数据库查询后的对象，
    是否是股东名称，是股东，返回股东对应符号，不是相反；
     */
    @Override
    public String checkUserType(List<User> users) {
        if (users != null){
            for ( User u:users) {
                if(u.getUsername().equals("唐飞") || u.getUsername().equals("马骏") || u.getUsername().equals("赵杰")){
                    //如果股东,返回股东类型;
                    return "1";   //股东返回1
                }else {
                    return "2";   //其他人返回2;
                }
            }
        }
        return "0";  //否则返回0;代表空数据;
    }


    /*
   3.制作登录后的seesion,在一定时间内,免登录;
    */
    @Override
    public Cookie makeCookie(HttpServletRequest req, HttpSession session) {
        Cookie cookie = null;
        if(session != null){
            String id = session.getId(); //服务器生成seesionID;
            cookie = new Cookie("JSESSIONID",id);//生成对应的seesionID；存入cookie,以便【这一次回话】可以使用;
            cookie.setMaxAge(60*60*60);   //设置cookie时间;
            return cookie;    //返回seesion对应的cookie;
        }
        return cookie;
    }
}
