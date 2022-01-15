package utils.Xls;


import dao.ReadXls;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/*
     poi读取xlsx
 */
public class Readxlsx implements ReadXls {

    @Override
    public List<?> readxls(String filename, String sheetname, String Separator, String placeholder){
//        String path="W:\\Java\\jar包\\execl-poi\\123.xlsx";
        //"Worksheet"
            String path = "W:\\\\Java\\\\jar包\\\\execl-poi\\\\123.xlsx";
            XSSFWorkbook workbook =null;
            try {
                FileInputStream fileInputStream = new FileInputStream(path);
                workbook = new XSSFWorkbook(fileInputStream);
                XSSFSheet sheet = workbook.getSheet("Worksheet");

                int maxRow = sheet.getLastRowNum();

                for(int i=0;i<maxRow;i++){
                    XSSFRow row = sheet.getRow(i);
                    if(row!=null){
                        int maxRol = sheet.getRow(i).getLastCellNum();
                        for(int j=0;j<maxRol;j++){
                            XSSFCell cell = row.getCell((short) j);
                            System.out.print(cell+Separator);
                        }
                        System.out.println("");
                    }
                }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String makeHeaddress(String filename) {
        return null;
    }

    @Override
    public List<?> addfield(String name, List<String> list) {
        return null;
    }

    @Override
    public List<?> Filterdata(List<String> list, int fieldnumber1, int fieldnumber2, String condition1, String condition2, String Separator) {
        return null;
    }

    @Override
    public List<?> javabean_transformation_list(List list, String fengefu) {
        return null;
    }

    @Override
    public List<?> read_field_name(String filename, String sheetname, String Separator, String placeholder) {
        return null;
    }
}
