//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.ReadXls;
import utils.Xls.Readxlsx;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws IOException {
//        String path="J:\\2021流程统计表.xlsx";
//        FileInputStream fileInputStream = new FileInputStream(path);

//        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
//        XSSFSheet sheet = workbook.getSheet("2021-7");
//        int maxRow = sheet.getLastRowNum();
//        for(int i=0;i<maxRow;i++){
//            XSSFRow row = sheet.getRow(i);
//            if(row!=null){
//                int maxRol = sheet.getRow(i).getLastCellNum();
//                for(int j=0;j<maxRol;j++){
//                    XSSFCell cell = row.getCell((short) j);
//                    System.out.print(cell+"\t");
//                }
//                System.out.println("");
//            }
//
//        }
//        workbook.close();

//        Workbook book = null;
//        book = new XSSFWorkbook(fis2);
////          book = new HSSFWorkbook(new FileInputStream(fis2));
//          Sheet sheet = book.getSheet("2021-7");
//        int rows = sheet.getPhysicalNumberOfRows();
//        for (int i = 0; i < rows; i++) {
//            Row row = sheet.getRow(i);
//            if (row != null) {
//                int cells = row.getPhysicalNumberOfCells();
//                String value = "";
//                for (int j = 0; j < cells; j++) {
//                    Cell cell = row.getCell(j);
//                    System.out.print(cell.getNumericCellValue());
//                }
//                }
//            }

//        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fis);
//        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
//        HSSFRow hssfRow = hssfSheet.getRow(0);
//        StringBuffer stringBuffer = new StringBuffer();
//        for(int j=0;j<hssfRow.getLastCellNum();j++) {
//            stringBuffer.append(hssfRow.getCell(j));
//            int fc = hssfRow.getLastCellNum()-1;
//            if(j != fc) {
//                stringBuffer.append(",");
//            }
//        }
//        System.out.println(stringBuffer);
//    }


//        String tonken="0e631b5344bb3e5eb1e7454236dd56e5e4d282bf";
//        String name="混合罐";
//        //String uri="https://www.patenthub.cn/api/s?ds=cn&t=xx&q=xx&v=1";
//        String uri = "https://www.patenthub.cn/api/s?ds=cn&t="+tonken+"&q="+name+"&v=1&p=5";
//        System.out.println(uri);
//        URL url = new URL(uri);
//        URLConnection urlConnection = url.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//        String urlString = "";
//        String current;
//        while( (current = in.readLine()) != null)
//        {
//            urlString += current;
//        }
//        System.out.println(urlString);

//        Properties properties = new Properties();
//        InputStreamReader inputStreamReader = new InputStreamReader();
//        properties.load();

        ReadXls readXls = new Readxlsx();
        readXls.readxls("W:\\\\Java\\\\jar包\\\\execl-poi\\\\123.xlsx","Worksheet","\t","null");

    }
}
