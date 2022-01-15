package service.LiuCheng;

import dao.OperationDatabase;
import javabean.Undelivered_manuscript;
import utils.DB.Driverutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQL_crud_Undelivered_manuscript implements OperationDatabase {

    /*
        插入给定的数据,到daycount交稿未完成的表内;
     */

    @Override
    public void insert(List<String> list) {
        Connection connection = null;   //数据库链接对象
        PreparedStatement preparedStatement = null;  //执行sql对象;

        //生成insert-sql代码
        String sql = "insert into DayCount(casetype,comittime,casename,neibu,waibu,status,gudong)VALUES(?,?,?,?,?,?,?)";

        //通过自己写的【Driverutils类中的方法，通过依次获取数据库连接对象，从而获取sql执行对象,那个防止sql注入的
        connection= Driverutils.GetConnection();  //获取数据库连接对象
        preparedStatement = Driverutils.GetprepareStatement(connection,sql);//同时传入sql语句

            try {
                //开始设置存入的值;
                for (String str : list) {
                    //分隔符,提取分段值;
                    String[] split = str.split("\t");
                    preparedStatement.setString(1, split[2]);   //类型
                    preparedStatement.setString(2, split[3]);  //交稿时间
                    preparedStatement.setString(3, split[4]);  //案件名称
                    preparedStatement.setString(4, split[6]);  //公司撰写人
                    preparedStatement.setString(5, split[7]);  //外部撰写人
                    preparedStatement.setString(6, split[8]);  //完成状态
                    preparedStatement.setString(7, split[split.length - 1]);  //案件交付人
                    preparedStatement.executeLargeUpdate();   //执行sql;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }finally {
                Driverutils.close(connection,preparedStatement); //关闭数据库连接对象和sql执行对象
            }
        }
    /*
        1.查询数据,给流程人员查看，主要是未交稿检索的;
        2.主要查询的是status列不是未完成的;这样的话，在存入数据库的时候，需要提前做数据校验，就是必须是已经分配的案子，没有分配的案子，不录入数据库
     */
    @Override
    public List<Undelivered_manuscript> select(String sql, String name) {
        Connection connection = null;   //数据库链接对象
        PreparedStatement preparedStatement = null;  //执行sql对象

        //通过自己写的【Driverutils类中的方法，通过依次获取数据库连接对象，从而获取sql执行对象,那个防止sql注入的；
        //获取数据库连接对象
        connection= Driverutils.GetConnection();
        //同时传入sql语句
        preparedStatement = Driverutils.GetprepareStatement(connection,sql);

        //创建个集合
        List<Undelivered_manuscript> undelivered_manuscript = new ArrayList<>();


        try {
            //开始查询sql
            ResultSet resultSet = preparedStatement.executeQuery();

            //判断是否查询到了数据
            while (resultSet.next()) {

                //开始取查询出来的数据;
                String casetype  = resultSet.getString("casetype");   //专利类型
                String comittime = resultSet.getString("comittime");  //交稿时间
                String casename  = resultSet.getString("casename");   //专利名称
                String neibu     = resultSet.getString("neibu");      //内部撰写人
                String waibu     = resultSet.getString("waibu");      //外部撰写人
                String status    = resultSet.getString("status");     //完成状态
                String gudong    = resultSet.getString("gudong");     //案件交接负责人；
                undelivered_manuscript.add(new Undelivered_manuscript().setCasetype(casetype).setComittime(comittime).setCasename(casename).setNeibu(neibu).setWaibu(waibu).setStatus(status).setGudong(gudong));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Driverutils.close(connection,preparedStatement); //关闭数据库连接对象和sql执行对象
        }

        return undelivered_manuscript;   //返回集合数据
    }

    @Override
    public void update() {
    }

    @Override
    public void del() {
    }

    @Override
    public String whichfield(String Fruit) {
        return null;
    }
}
