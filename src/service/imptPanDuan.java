package service;

import dao.PanDuan;

public class imptPanDuan implements PanDuan {
    /*
   1.判断是在linux系统还是window系统
    */
    @Override
    public String if_winORlinux() {
        String property = System.getProperty("user.dir");   //获取java程序当前路径;
        if(property.indexOf("\\") >0){   //如果当中包含了:，代表是win
            return "win";
        }else {
            return "linux";
        }
    }
}
