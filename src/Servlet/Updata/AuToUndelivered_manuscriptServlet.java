package Servlet.Updata;

import dao.OperationDatabase;
import dao.UpdateXls;
import utils.Xls.Updatexls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet("/AuToUndelivered_manuscriptServlet")
public class AuToUndelivered_manuscriptServlet extends HttpServlet {
    /*
         分配了撰写对象，但是未完成的案件数据更新接口；
         流程人员的数据接口；
       */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //curl命令传参的，进行utf-8编码的，所以需要解码
        String execl_dir = URLDecoder.decode(req.getParameter("execl_dir"), "UTF-8");
        String[] execl_dirs = execl_dir.split("、");//获取文件数组;

        //curl命令传参的，进行utf-8编码的，所以需要解码
        String sheet_name = URLDecoder.decode(req.getParameter("sheet_name"), "UTF-8");
        String[] sheet_names = sheet_name.split("、"); //获取月份数组;

        OperationDatabase.cleartable("DayCount");  //更新之前清空下拉;

        UpdateXls update = new Updatexls();  //多态
        update.update_incomplete_data(execl_dirs,sheet_names);    //开始更新;

        PrintWriter writer = resp.getWriter();
        writer.write("update_OK");   //给客户端返回更新成功提示
    }

    /*
       服务器上通过shell脚本实现url传参，启动更新数据；
    */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        req.setCharacterEncoding("utf-8");

        //curl命令传参的，进行utf-8编码的，所以需要解码
        String execl_dir = URLDecoder.decode(req.getParameter("execl_dir"), "UTF-8");
        String[] execl_dirs = execl_dir.split("、");//获取文件数组;

        //curl命令传参的，进行utf-8编码的，所以需要解码
        String sheet_name = URLDecoder.decode(req.getParameter("sheet_name"), "UTF-8");
        String[] sheet_names = sheet_name.split("、"); //获取月份数组;

        OperationDatabase.cleartable("DayCount");  //更新之前清空下拉;

        UpdateXls update = new Updatexls();  //多态
        update.update_incomplete_data(execl_dirs,sheet_names);    //开始更新;

        PrintWriter writer = resp.getWriter();
        writer.write("update_OK");   //给客户端返回更新成功提示
    }
}
