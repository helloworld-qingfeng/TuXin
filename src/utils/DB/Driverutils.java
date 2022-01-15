package utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Driverutils {

    //获取数据库连接对象
    public static Connection GetConnection(){
        //获取数据库连接对象
        Connection Cone = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");  //注册数据库驱动；
            //获取数据库连接对象
            Cone = getConnection("jdbc:mysql://39.98.114.178:3306/TuXin?useUnicode=true&characterEncoding=utf-8", "root", "123456");
            return Cone;  //返回数据库连接对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Cone;  //返回数据库连接对象
    }

    //获取sql执行对象;-防sql注入那个对象
    public static PreparedStatement GetprepareStatement(Connection connection,String sql) {
        //获取sql执行对象;-防sql注入那个对象
        PreparedStatement preparedStatement = null;
        try {
            if(connection != null && sql.length() != 0){  //判断传入的connection对象和sql均是否为空;
                //获取sql执行对象;-防sql注入那个对象、并且传入sql
                preparedStatement = connection.prepareStatement(sql);
                return preparedStatement;  //返回sql执行对象；
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement; //返回sql执行对象；
    }


    //关闭数据库连接对象、sql执行对象
    public static  void close(Connection connection,PreparedStatement preparedStatement){

        try {
            if(connection != null && preparedStatement != null){   //判断传入的对象是否为空;
                preparedStatement.close();//关闭哦;
                connection.close();//关闭哦;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
