package utils.Xls;

import dao.OperationDatabase;
import dao.ReadXls;
import dao.UpdateXls;
import service.GuDong.MySQL_crud_GuDong;
import service.LiuCheng.MySQL_crud_LiuCheng;
import service.LiuCheng.MySQL_crud_Undelivered_manuscript;

import java.util.List;

public class Updatexls implements UpdateXls{

    /*
    数据更新接口,用于股东数据更新，
    A.传入xlsx文件名称数组(前端传入)
    B.传入sheet名称数组(前端传入)
      1.即按照指定的目录下的xlsx表,
      2.去找指定的月份sheet
      3.然后存入股东查询数据库中;
    */
    @Override
    public void update_Gudong_data(String[] xlsnames,String[] sheetnames) {
        OperationDatabase operationDatabase = new MySQL_crud_GuDong();//多态
        ReadXls read = new Readxls(); //多态
        //以xls文件数量、sheet名称数量进行循环插入；
        for (String xlsname:xlsnames) {
            //提取表前缀;
            String xls_name = read.makeHeaddress(xlsname);  //获取表前缀(股东名称)，以生成字段使用
            for ( String sheetname:sheetnames) {
                //读取数据;返回集合
                List<String> strings = (List<String>) read.readxls(xlsname, sheetname, "\t", "null");
                //生成带有表前缀(股东名称)的集合,添加股东名称形成新字段,并且返回新list集合
                List<String> addfield = (List<String>) read.addfield(xls_name, strings);
                //插入集合;
                operationDatabase.insert(addfield);//插入数据;
            }
        }
    }

    /*
    数据更新接口,用于 未完成案件 数据更新，
       1.生成一个读取xls文件的对象,按照指定的xls文件和sheet表名称，读取并且返回一个list集合
       2.对第一步步骤的数据进行过滤，得出被分配过的案子的，并且返回一个集合
       3.将步骤2返回的集合，插入到数据库中
     */
    @Override
    public void update_incomplete_data(String[] xlsnames,String[] sheetnames) {
        ReadXls readXls = new Readxls(); //生成
        OperationDatabase operationDatabase = new MySQL_crud_Undelivered_manuscript();  //生成
        for (String xlsname : xlsnames) {
            String xslfilename = readXls.makeHeaddress(xlsname); //提取股东名称
            for ( String sheetname:sheetnames) {
                //读取数据;
                List<String> str = (List<String>) readXls.readxls(xlsname, sheetname, "\t", "null");
                //增加股东名称字段;
                List<String> addfieldlist = (List<String>) readXls.addfield(xslfilename, str);
                //过滤数据
                List<String> filterdata = (List<String>) readXls.Filterdata(addfieldlist, 6, 7, "null", "null", "\t");
                //插入数据;
                operationDatabase.insert(filterdata);
            }
        }

    }


    /*
    数据更新接口,用于代报案件数据更新;
   1.生成一个读取xls文件的对象,按照指定的xls文件和sheet表名称，读取并且返回一个list集合
   2.list集合插入指定数据库liucheng
 */
    @Override
    public void update_Declare_case(String[] xlsnames, String[] sheetnames) {
        ReadXls readXls  = new Readxls(); //多态
        OperationDatabase operationDatabase = new MySQL_crud_LiuCheng();
        for (String xlsname : xlsnames) {
            for ( String sheetname:sheetnames) {
                List<String> liuchenglist = (List<String>) readXls.readxls(xlsname, sheetname, "\t", "null");
                operationDatabase.insert(liuchenglist);
            }
        }
    }
}
