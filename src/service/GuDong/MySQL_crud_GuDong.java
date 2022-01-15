package service.GuDong;

import dao.OperationDatabase;
import javabean.Case;
import utils.DB.Driverutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQL_crud_GuDong implements OperationDatabase {

    /*
      1.给一个list集合，将这个集合存入到股东查询的数据库内;以提供后续查询;
     */
    @Override
    public void insert(List<String> list) {
        Connection connection = null;    //数据库连接对象
        PreparedStatement preparedStatement = null;  //执行sql对象

        //生成insert-sql代码
        String sql = "insert into CaseMsg(customer,Casetype,CaseName,SubmitTime,DuralTheConveyancer,Outsourcer,NameOfShareholder,CaseReceivingPeriod) values(?,?,?,?,?,?,?,?)";

        //获取数据库连接对象
        connection= Driverutils.GetConnection();
        //通过自己写的【Driverutils类中的方法，通过依次获取数据库连接对象，从而获取sql执行对象,那个防止sql注入的】
        preparedStatement = Driverutils.GetprepareStatement(connection,sql);//同时传入sql语句

        try {
            for ( String name : list) {
                String[] split = name.split("\t");
                preparedStatement.setString(1,split[0]); //客户名称
                preparedStatement.setString(2,split[2]); //第三个元素,案件类型
                preparedStatement.setString(3,split[4]); //专利名称
                preparedStatement.setString(4,"null");
                preparedStatement.setString(5,split[6]); //公司撰写人
                preparedStatement.setString(6,split[7]); //外包人;
                preparedStatement.setString(7,split[split.length-1]);//股东名称
                preparedStatement.setString(8,split[split.length-2]);//接案时间
                preparedStatement.executeLargeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Driverutils.close(connection,preparedStatement);  //关闭数据库连接对象和执行sql的对象
        }
    }

    /*
        1.股东admin查询所使用的接口;
        2.将查询到的数据，返回一个list集合，case泛型，提供html展示
     */
    @Override
    public List<Case> select(String sql,String name) {

        //创建list集合;
        List<Case>  cases = new ArrayList<>();


        //获取数据库连接对象;
        Connection connection = Driverutils.GetConnection();

        //获取执行sql的对象,防止sql注入那个;
        PreparedStatement preparedStatement = Driverutils.GetprepareStatement(connection, sql);


        try {
            preparedStatement.setString(1,"%"+name+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String customer = resultSet.getString("customer");    //客户名称
                String Casetype = resultSet.getString("Casetype");    //案件类型
                String CaseName = resultSet.getString("CaseName");    //案件名称
                String DuralTheConveyancer = resultSet.getString("DuralTheConveyancer"); //内部处理人
                String Outsourcer = resultSet.getString("Outsourcer");  //外包人;
                String NameOfShareholder = resultSet.getString("NameOfShareholder");  //股东名称
                String CaseReceivingPeriod = resultSet.getString("CaseReceivingPeriod"); //接案时间
                cases.add(new Case().setCustomer(customer).setCasetype(Casetype).setCasename(CaseName).setDuraltheconveyancer(DuralTheConveyancer).setOutsourcer(Outsourcer).setNameofshareholder(NameOfShareholder).setCaseReceivingperiod(CaseReceivingPeriod));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Driverutils.close(connection,preparedStatement);
        }

        //开始查询;
        return cases;

    }


    @Override
    public void update() {

    }

    @Override
    public void del() {

    }

    /*
       1.判断到底查询哪个列;
     */
    @Override
    public String whichfield(String Fruit) {
        if (Fruit.equals("CaseName")) {
            return "CaseName";
        } else if (Fruit.equals("Casetype")) {
            return "Casetype";
        } else if(Fruit.equals("DuralTheConveyancer")){
            return "DuralTheConveyancer";
        }else {
            return "Outsourcer";
        }
    }
}
