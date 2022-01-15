package dao;

import utils.DB.Driverutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OperationDatabase {
    /*
     定义操作数据库的方法
 */

    //插入sql;
    abstract void insert(List<String> list);

    //查询sql;
    abstract List<?> select(String sql, String name);

    //更改sql;
    abstract void update();

    //删除sql
    abstract void del();

    //查询那个列;
    abstract String whichfield(String Fruit);

    //清空表;
    //静态方法
    public static boolean cleartable(String tablename){
        Connection connection = null;    //数据库连接对象
        PreparedStatement preparedStatement = null;  //执行sql对象
        boolean execute = false;
        //获取数据库连接对象
        connection= Driverutils.GetConnection();
        String sql = "truncate table"+" "+tablename;
        //通过自己写的【Driverutils类中的方法，通过依次获取数据库连接对象，从而获取sql执行对象,那个防止sql注入的】
        preparedStatement = Driverutils.GetprepareStatement(connection,sql);//同时传入sql语句
        try {
            execute = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Driverutils.close(connection,preparedStatement);  //关闭数据库连接对象和执行sql的对象
        }
        return execute;
    }
}
