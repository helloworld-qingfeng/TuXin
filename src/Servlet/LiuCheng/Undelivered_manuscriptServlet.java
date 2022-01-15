package Servlet.LiuCheng;

import dao.OperationDatabase;
import dao.ReadXls;
import dao.WriteXls;
import javabean.Undelivered_manuscript;
import service.LiuCheng.MySQL_crud_Undelivered_manuscript;
import service.LiuCheng.Write_Xls_LiuCheng;
import service.imptPanDuan;
import utils.Xls.Readxls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

@WebServlet("/Undelivered_manuscript")
public class Undelivered_manuscriptServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /*
     1.未交稿查询的servelet
    */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");  //设置编码
        long startTime = System.currentTimeMillis(); //获取当前时间;

        //sql语句
        String sql = "SELECT * FROM  DayCount  WHERE status != \"已完成\"";
        OperationDatabase operationDatabase = new MySQL_crud_Undelivered_manuscript();   //多态

        //查询结果的封装对象
        List<Undelivered_manuscript> undelivered_manuscript = (List<Undelivered_manuscript>) operationDatabase.select(sql, "");
        String[] tilte = {"专利类型","交稿时间","专利名称","内部撰写人","外部撰写人","完成状态","案件交接负责人"};
        ReadXls readXls = new Readxls();
        List<String> aNull = (List<String>) readXls.javabean_transformation_list(undelivered_manuscript, "\t");
        WriteXls writeXls = new Write_Xls_LiuCheng();

        Calendar cal = Calendar.getInstance();  //实例化一个对象
        int year = cal.get(Calendar.YEAR);  //获取年份
        int month = cal.get(Calendar.MONTH) + 1;  //提取当月的月份;
        int day = cal.get(Calendar.DAY_OF_MONTH);//提取当月的日
        String filename =year+"-"+month+"-"+day+".xls";  //文件名称
        String path ="/file_dir/";  //目录
        String file_input_dir = req.getServletContext().getRealPath(path+filename);  //获取完整地址;
//        System.out.println(file_input_dir);
        //开始写文件;
        writeXls.writexls(aNull,"\t","未完成专利",file_input_dir,tilte);

        //地址分割;
        String[] split = null;
        String s = new imptPanDuan().if_winORlinux();

        if("linux".equals(s)){  //判断是在啥系统;
            split = file_input_dir.split("/");  //linux系统采用/分割
        }else {
            split = file_input_dir.split("\\\\");  //windows采用\\分割
        }

//        System.out.println(split[split.length - 1]);
        //参数生成,并且生成“url尾部可携带的乱码”;
        String server_file_path = URLEncoder.encode(split[split.length - 1], "utf-8");
//        System.out.println(server_file_path);

        long endTime = System.currentTimeMillis();    //获取结束时间;
        DecimalFormat df = new DecimalFormat("0.0000");//设置保留位数;
        String time = df.format((float) (endTime - startTime) / 1000);  //毫秒转秒;


        /*
           1.重定向，一次会话间;
         */
//        HttpSession session = req.getSession();
//        session.setAttribute("time",time);//设置查询时间的域对象
//        session.setAttribute("undelivered_manuscript",undelivered_manuscript);  //设置查询结果的域对象
//        session.setAttribute("server_file_path",server_file_path);  //设置查询结果的域对象
//        resp.setStatus(302);
//        resp.sendRedirect(req.getContextPath()+"/LiuCheng.jsp"); //重定向

        /*
            一次请求,转发
         */
        req.setAttribute("time",time);   //设置查询时间的域对象
        req.setAttribute("undelivered_manuscript",undelivered_manuscript);  //设置查询结果的域对象
        req.setAttribute("server_file_path",server_file_path);  //设置查询结果的域对象
        req.getRequestDispatcher(req.getContextPath() + "/LiuCheng.jsp").forward(req,resp);   //转发
    }
}
