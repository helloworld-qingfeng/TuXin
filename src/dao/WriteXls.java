package dao;

import java.util.List;

public interface WriteXls  {
    /*
        1.写一个xls文件;
     */
    abstract void writexls(List<String> list, String fengefu, String sheetname, String file_input_dir, String[] title);
}
