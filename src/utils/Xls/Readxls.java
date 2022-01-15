package utils.Xls;

import dao.ReadXls;
import javabean.Undelivered_manuscript;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
     1,jxl的读取方式;
 */
public class Readxls implements ReadXls{

    /*
    使用方式：new ReadXls(xls文件名称,sheet名称)
      1.将指定的xlsx表中指定的sheet工作表取出,返回一个list<String>集合;
      2.在xlsx表中原有字段基础上,增加【sheet表名称】字段
   */
    @Override
    public List<String> readxls(String filename, String sheetname,String Separator,String placeholder) {
        List<String> list = null;
        Workbook workbook = null;
        File file = null;
        Sheet sheet = null;


        try {
            file = new File(filename);
            WorkbookSettings ws = new WorkbookSettings();  //去除wookbook的读取excel时数据的有效性
            ws.setCellValidationDisabled(true);           //去除wookbook的读取excel时数据的有效性

            workbook = Workbook.getWorkbook(file,ws);  //获取execl的工作簿;
            sheet = workbook.getSheet(sheetname);      //获取指定的工作表;sheet;

            list = new ArrayList<>();  //临时容器;

            int rows = sheet.getRows();         //获取多少行;
            int columns = sheet.getColumns();   //获取多少行列

            for (int i = 1; i < rows; i++) {          //从第二行开始,不要字段头，默认第一行是0；所以从1开始；
                String date = "";

                //1.开始生成行数据
                for (int j = 0; j < columns; j++) {     //从第一列开始；默认第一列是0；
                    Cell cell1 = sheet.getCell(j, i);   //根据第几行、第几列 取出具体的 单元格[对象]
                    String result = cell1.getContents();   //调用单元格对象中的方法，获取单元格数据

                    //判断这个单元格是否为空,
                    if (result.length() == 0) {
                        result = placeholder;             //为空,替换为指定的占位符;方便后续存入数据库
                    }else if("包授权发明".equals(result)){
                        result = "高质量发明";
                    }
                    //每个单元格拼接在一起，组成 行
                    date += result + Separator;     //连接上指定的 分隔符;
                }

                //每个行数据后面增加 年月份；
                date += sheetname + Separator;

                //2.判断行数据;
                //2.1 每行数据进行采用空分割，返回该行的一个数据数组；
                String[] split = date.split(Separator);
                //2.2 判断行头是不是null字符串，是的话，代表这行数据没有意义
                if (! placeholder.equals(split[0])) {
                    //2.3 非空,有意义,添加到list集合;
                    list.add(date);
                }
            }

            return list;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

        return list;   //返回数据数组
    }


    /*
    使用方式：new make_name( xls文件名称 )
       1.针对股东xls文件，取出股东名称，并返回；
   */
    @Override
    public String makeHeaddress(String filename) {
        String name = null;
        if (filename.contains("\\")) {   //判断分隔符,\\代表是windows系统,
            String[] Astrings = filename.split("\\\\"); //采用windows系统分隔符进行分割;
            String strings1 = Astrings[Astrings.length - 1];  //取最后一个,就是文件名称那个;
            String[] split = strings1.split("-");  //再用-作为分隔符,进行分割,取出人名;
            name = split[0];  //取出人名;
            return name; //返回人名;

        } else if (filename.contains("/")) {  //判断分隔符,/代表是linux系统,
            String[] Astrings = filename.split("/");  //采用linux系统分隔符进行分割;
            String strings1 = Astrings[Astrings.length - 1];   //取最后一个,就是文件名称那个;
            String[] split = strings1.split("-");  //再用-作为分隔符,进行分割,取出人名;
            name = split[0];   //取出人名;
            return name;   //返回人名;
        }else {
            return name;
        }
    }


    /*
   使用方式：
     1.接收一个xls数据的集合，将该xls数据行尾端追加新字段；
    */
    @Override
    public List<String> addfield(String name, List<String> list) {
        List<String> addlist = new ArrayList<String>();
        for (String str:list) {
            str+=name;
            addlist.add(str);
        }
        return addlist;
    }


    /*
 4.传入指定的list集合数据，对list集合中的每个元素进行过滤，返回过滤后的数据list集合;
  双列匹配过滤
  int是列号，即第几列；
 conditions是条件,比如第三字段的某个单元格是这个条件；
 String Separator  分隔符;，形成单元格的重要依据
  */
    @Override
    public List<String> Filterdata(List<String> list,int fieldnumber1,int fieldnumber2,String condition1,String condition2,String Separator) {
        List<String> filterlist = new ArrayList<String>();
        for (String str:list) {   //获取list集合中的每行;
            String[] split = str.split(Separator);  //将每行按照指定的拆分成独立的元素;
            System.out.println(str);
            //判断其不等值;
            //1.第split[fieldnumber1]字段=不等于condition1
            // 或者
            //1.第split[fieldnumber2]字段=不等于condition2
            //则条件达成
            if( !condition1.equals(split[fieldnumber1]) || !condition2.equals(split[fieldnumber2])){
                System.out.print(split[fieldnumber1]+":");
                System.out.println(split[fieldnumber2]);
                filterlist.add(str);  //添加到过滤后的集合内；
            }
        }
        return filterlist;
    }

    /*
        1.给一个javabean<Undelivered_manuscript>的集合，将其转换为list<String>类型;以方便写xls文件到本地;
        2.采用 fengefu 作为指定的分隔符：
     */

    @Override
    public List<String> javabean_transformation_list(List list,String fengefu) {
        List<String> strings = new ArrayList<String>();
        List<Undelivered_manuscript> listu = (List<Undelivered_manuscript>) list;  //强制转换;
        for (Undelivered_manuscript jb:listu) {
            String casetype = jb.getCasetype();
            String comittime = jb.getComittime();
            String casename = jb.getCasename();
            String neibu = jb.getNeibu();
            String waibu = jb.getWaibu();
            String status = jb.getStatus();
            String gudong = jb.getGudong();
            strings.add(casetype+fengefu+comittime+fengefu+casename+fengefu+neibu+fengefu+waibu+fengefu+status+fengefu+gudong+fengefu);
        }
        return strings;
    }




    /*
        6.读取一个xls表格，只读取他的字段头即可；返回一个list集合;
      */
    @Override
    public List<String> read_field_name(String filename, String sheetname, String Separator, String placeholder) {

        List<String> list = null;
        Workbook workbook = null;
        File file = null;
        Sheet sheet = null;

        try {
            file = new File(filename);

            WorkbookSettings ws = new WorkbookSettings();  //去除wookbook的读取excel时数据的有效性
            ws.setCellValidationDisabled(true);           //去除wookbook的读取excel时数据的有效性

            workbook = Workbook.getWorkbook(file,ws);  //获取execl的工作簿;
            sheet = workbook.getSheet(sheetname);      //获取指定的工作表;sheet;

            list = new ArrayList<>();  //临时容器;

            //就读1行,默认下标索引是0；
            //int rows = sheet.getRows();         //获取多少行;
            int columns = sheet.getColumns();   //获取多少行列

            String title = "";  //定义字段title字符串
                //1.开始生成行数据
                for (int j = 0; j < columns; j++) {     //从第一列开始；默认第一列是0；
                    Cell cell1 = sheet.getCell(j, 0);   //根据第1行、第几列 取出具体的 单元格[对象]
                    String result = cell1.getContents();   //调用单元格对象中的方法，获取单元格数据
                    //判断这个单元格是否为空,
                    if (result.length() == 0) {
                        result = placeholder;             //为空,替换为指定的占位符;方便后续存入数据库
                    }else if("包授权发明".equals(result)){
                        result = "高质量发明";
                    }
                    //每个单元格拼接在一起，组成 行
                    title += result + Separator;     //连接上指定的 分隔符;
                }

            list.add(title);  //装集合;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return list;
    }

}
