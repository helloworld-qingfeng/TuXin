package Servlet.LiuCheng;

import dao.OperationDatabase;
import javabean.LiuCheng;
import service.LiuCheng.MySQL_crud_LiuCheng;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@WebServlet("/JiaoFei")
public class LiuChengSearchServelt extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /*
       临近缴费临界点，未缴费的案子输出查询;
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");  //设置编码

        long startTime = System.currentTimeMillis(); //获取当前时间;

        OperationDatabase operationDatabase = new MySQL_crud_LiuCheng();//多态
        //生成insert-sql代码
         String sql = "select * from liucheng where STATUS != \"已缴费\" ";
        List<LiuCheng> liuchengsearchdb = (List<LiuCheng>) operationDatabase.select(sql, "");//传入sql即可；

        long endTime = System.currentTimeMillis();    //获取结束时间
        DecimalFormat df=new DecimalFormat("0.0000");//设置保留位数
        String time = df.format((float) (endTime - startTime) / 1000);  //毫秒转秒

        /*
            一次会话
         */
//        HttpSession session = req.getSession();
//        session.setAttribute("time",time);   //设置查询时间的域对象
//        session.setAttribute("liuchengsearchdb",liuchengsearchdb);  //设置查询结果的域对象
//         resp.setStatus(302);
//         resp.sendRedirect(req.getContextPath()+"/LiuCheng.jsp"); //重定向

        /*
          一次请求
         */
        req.setAttribute("time",time);   //设置查询时间的域对象
        req.setAttribute("liuchengsearchdb",liuchengsearchdb);  //设置查询结果的域对象
        req.getRequestDispatcher(req.getContextPath() + "/LiuCheng.jsp").forward(req,resp);   //转发
    }
}
