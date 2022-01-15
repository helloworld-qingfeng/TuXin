package dao;

public interface GetResources {
    /*
       通过给定的url，获取json数据、地址、tonken等信息；实现其他网站接口的信息查询和文件获取下载；
     */
    abstract String get_tonken(String url);
}
