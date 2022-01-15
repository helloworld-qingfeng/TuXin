package service.LiuCheng;

import dao.WriteXls;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Write_Xls_LiuCheng implements WriteXls {

     /*
        1.写一个xls文件到指定目录下;
     */
    @Override
    public void writexls(List<String> list,String fengefu,String sheetname,String file_input_dir,String[] title) {
        // 创建Excel文件
        File file = new File(file_input_dir);   //文件写到哪个位置;
        WritableWorkbook workbook = null;
        WritableSheet sheet = null;
        try {
            // 创建Excel文件
            file.createNewFile();

            // 创建工作薄workBook
            workbook = Workbook.createWorkbook(file);
            // 创建工作表sheet
           sheet = workbook.createSheet(sheetname,0);

            Label label = null;  //label对象
            /*
             * column：列 row：行 content:内容 JXL没有直接针对单元格的操作，直接对行 或者 列进行写入，如果操作单元格
             * 会有些复杂。
             */
            // Label label2=new Label(column(字段), row(行), content(单元格数据体));

        // 第一行设置列名;
            for (int i = 0; i < title.length; i++) {
                label = new Label(i, 0, title[i]);
                sheet.addCell(label);
            }


         //写入数据;
            for(int row=1;row <= list.size();row++){  //定义行;
                String[] split = list.get(row - 1).split(fengefu); //获取对应的数据行;
                for (int filed = 0; filed < title.length; filed++) {   //定义列
                    label = new Label(filed, row, split[filed]);  //第n【行】,第1列;第2列;第3列;第4列;
                    sheet.addCell(label);   //添加数据到sheet
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch (WriteException e){
            e.printStackTrace();
        }finally {
            try {
                workbook.write();
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (WriteException e){
                e.printStackTrace();
            }
        }
    }
}
