package dao;

public interface UpdateXls {
    /*
       1.更新股东数据表;
         xlsnames:文件名称数组
         sheetnames：sheet工作铺数组
     */
    abstract void update_Gudong_data(String[] xlsnames, String[] sheetnames);

    /*
       2.更新未完成数据库表
     */
    abstract void update_incomplete_data(String[] xlsnames, String[] sheetnames);

    /*
        3.更新申报案件流程表数据;
     */
    abstract void update_Declare_case(String[] xlsnames, String[] sheetnames);
}
