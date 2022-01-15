package dao;

import java.util.List;

public interface ReadXls{
    /*
      1.读取xls文件，并且返回集合：
           filename：文件名称
           sheetname：工作簿名字
           Separator:分隔符;
           placeholder:占位符;
      3.将指定的xlsx表中指定的sheet工作表取出,返回一个list<String>集合;
      4.在xlsx表中原有字段基础上,增加【sheet表名称】字段
     */

    abstract List<?> readxls(String filename, String sheetname, String Separator, String placeholder);


    /*
      2.提取xls文件的名称,因为有些xls表的名称是有股东名称的，以形成所谓的新的字段使用
     */
    abstract String makeHeaddress(String filename);


    /*
      3.对xls文件返回的集合，添加新的字段;并且返回集合：
     */
    abstract List<?> addfield(String name, List<String> list);


    /*
       4.传入指定的list集合数据，对list集合中的每个元素进行过滤，返回过滤后的数据list集合;
        双列匹配过滤
        int是列号，即第几列；
       conditions是条件,比如第三字段的某个单元格是这个条件；
       String Separator  分隔符;，形成单元格的重要依据
     */
    abstract List<?> Filterdata(List<String> list, int fieldnumber1, int fieldnumber2, String condition1, String condition2, String Separator);


    /*
       5.给一个javabean的集合，将其转换为list<String>类型;以方便写xls文件到本地;
     */
     abstract List<?> javabean_transformation_list(List list, String fengefu);


     /*
        6.读取一个xls表格，只读取他的字段头即可；返回一个list集合;
      */
     abstract List<?> read_field_name(String filename, String sheetname, String Separator, String placeholder);
}



