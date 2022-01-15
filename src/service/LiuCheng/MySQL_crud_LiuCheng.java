package service.LiuCheng;

import dao.OperationDatabase;
import javabean.LiuCheng;
import utils.DB.Driverutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQL_crud_LiuCheng implements OperationDatabase{


    /*
       1.给一个读取xls文件后存入到指定集合中的【集合对象】,将这个集合中的数据插入到指定的表中;
       2.插入的是TuXin库中的liucheng表中;
     */
    @Override
    public void insert(List<String> list) {
        Connection connection = null;   //数据库链接对象
        PreparedStatement preparedStatement = null;  //执行sql对象;

        //生成insert-sql代码
        String sql = "insert into liucheng(ShenQingRen,CaseName,CaseType,CaseNumber,ShenQingDate,ShenQingFeiJieZhiRi,ShenQingFeiJiaoFeiDate,ShiShenJiaoFeiRi,status,ShiShenRi,SheetName)VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        //通过自己写的【Driverutils类中的方法，通过依次获取数据库连接对象，从而获取sql执行对象,那个防止sql注入的
        connection= Driverutils.GetConnection();  //获取数据库连接对象
        preparedStatement = Driverutils.GetprepareStatement(connection,sql);//同时传入sql语句

        try {
                for ( String name : list) {
                    String[] split = name.split("\t");   //分隔符;
                    preparedStatement.setString(1,split[0]);    //申请人
                    preparedStatement.setString(2,split[1]);    //专利名称
                    preparedStatement.setString(3,split[2]);    //专利类型
                    preparedStatement.setString(4,split[3]);    //专利申请号
                    preparedStatement.setString(5,split[4]);    //申请日期
                    preparedStatement.setString(6,split[5]);    //申请费截止日
                    preparedStatement.setString(7,split[6]);    //申请费缴费日
                    preparedStatement.setString(8,split[7]);    //实审费缴费日
                    preparedStatement.setString(9,split[8]);    //目前状态
                    preparedStatement.setString(10,split[9]);   //实审日
                    preparedStatement.setString(11,split[split.length-1]); //sheet名称那个字段;

                    preparedStatement.executeLargeUpdate();   //执行sql;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                Driverutils.close(connection,preparedStatement); //关闭数据库连接对象和sql执行对象
            }
    }



    /*
     1.查询数据库,【TuXin库中的liucheng表】，排除已缴费的;
     2.对于未交费的,查询其记录的月份,如果该月份=当月月份;则进入判断,如果不等于,则认为最多还有一个月;
     3.进入判断后,查询当月当日最大天数，和数据库里的对比较；
     4.返回LiuCheng对象list对象;
      */
    @Override
    public List<LiuCheng> select(String sql, String name) {
        Connection connection = null;   //数据库链接对象
        PreparedStatement preparedStatement = null;  //执行sql对象

        //通过自己写的【Driverutils类中的方法，通过依次获取数据库连接对象，从而获取sql执行对象,那个防止sql注入的；
        //获取数据库连接对象
        connection= Driverutils.GetConnection();
        //同时传入sql语句
        preparedStatement = Driverutils.GetprepareStatement(connection,sql);

        //创建个集合
        List<LiuCheng> liuChengList = new ArrayList<>();


        /*
           1.提取当月、并且计算这个月 最大有多少天
         */
        Calendar cal = Calendar.getInstance();  //实例化一个对象
        int year = cal.get(Calendar.YEAR);  //获取年份
        int month = cal.get(Calendar.MONTH) + 1;  //提取当月的月份;
        cal.set(Calendar.YEAR, year);//年设置为当前年
        cal.set(Calendar.MONTH, month);//设置当前月
        int day = cal.getActualMaximum(Calendar.DATE); //提取当月的月份一共有多少天


        try {
            ResultSet resultSet = preparedStatement.executeQuery(); //开始查询sql

            //判断是否查询到了数据
            while (resultSet.next()) {

                //开始取查询出来的数据;
                String filed1 = resultSet.getString("ShenQingRen");  //申请人
                String filed2 = resultSet.getString("CaseName");   //专利名称
                String filed3 = resultSet.getString("CaseType");   //专利类型
                String filed4 = resultSet.getString("CaseNumber");   //专利申请号
                String filed5 = resultSet.getString("ShenQingDate");   //申请日期
                String filed6 = resultSet.getString("ShenQingFeiJieZhiRi");   //申请费截止日;
                String filed7 = resultSet.getString("ShenQingFeiJiaoFeiDate");   //申请费缴费日
                String filed8 = resultSet.getString("ShiShenJiaoFeiRi");  //实审费缴费日
                String filed9 = resultSet.getString("status");   //目前状态
                String filed10 = resultSet.getString("ShiShenRi");  //实审日
                String filed11 = resultSet.getString("SheetName");   //sheet字段

                if("缴费".equals(filed9)){

                }else {
                    //分割申请费截止日字段,取出月和日，判定是否到达临界缴费点;
                    if("null".equals(filed6)){ }else {
                        String[] split = filed6.split("/");
                        int mouth2;   //表格存储的月份
                        int day2;     //表格存储的日
                /*
                    判断  申请费截止日字段  是否是年-月-日；还是月-日
                 */
                        if (split.length == 2) {
                            //提取表格存储的月份、表格存储的日
                            mouth2 = Integer.parseInt(split[0]); //提取表格存储的月份
                            day2 = Integer.parseInt(split[split.length - 1]);   //提取表格存储的日
                        } else {
                            mouth2 = Integer.parseInt(split[1]); //提取表格存储的月份
                            day2 = Integer.parseInt(split[split.length - 1]);   //提取表格存储的日
                        }

                        //如果存储的月份与当月月份，一致，才进入判断;
                        if (month == mouth2) {
                            //如果当月当日日期大于或者等于存储的，要进行报警了哦;
                            if (day >= day2) {
                                liuChengList.add(new LiuCheng().setFiled1(filed1).setFiled2(filed2).setFiled3(filed3).setFiled4(filed4).setFiled5(filed5).setFiled6(filed6).setFiled7(filed7).setFiled8(filed8).setFiled9(filed9).setFiled10(filed10).setFiled11(filed11));
                            }
                        }
                    }
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Driverutils.close(connection,preparedStatement); //关闭数据库连接对象和sql执行对象
        }
        return liuChengList;
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
