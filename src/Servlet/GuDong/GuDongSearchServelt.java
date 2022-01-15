package Servlet.GuDong;

import dao.OperationDatabase;
import javabean.Case;
import service.GuDong.MySQL_crud_GuDong;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@WebServlet("/Search")
public class GuDongSearchServelt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String case_name = null;
        String Fruit = null;
        req.setCharacterEncoding("utf-8");   //设置编码格式
        case_name = req.getParameter("Case_name");  //获取admin.jsp里搜索框传来的数据;
        Fruit = req.getParameter("Fruit");  //获取admin.jsp里搜索框传来的数据
        if (case_name.length() == 0 || Fruit == null) {
            //至少一个参数是空的;
            req.setAttribute("error","error");  //返回错误页面所需的域对象；这个域对象作用一次请求间；
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(req.getContextPath() + "/admin.jsp");
            requestDispatcher.forward(req,resp); //转发的时候，顺带将response和request也转发过去;
        }else {
            //两个参数都不为空,进入下一步判断
            long startTime = System.currentTimeMillis(); //获取当前时间;
            OperationDatabase operationDatabase = new MySQL_crud_GuDong();//建立查询股东数据库的对象
            List<Case> search = null;  //创建一个引用对象,返回给前端用的域对象;

            OperationDatabase operationDatabase1 = new MySQL_crud_GuDong();
            String fieldname = operationDatabase.whichfield(Fruit);
            //定义sql; 专利[名称]查询的sql定义
            String sql = "select * from CaseMsg where " + fieldname + " LIKE ? ";

            //调用查询数据sql的方法;传入参数;
            search = (List<Case>) operationDatabase.select(sql,case_name);

            long endTime = System.currentTimeMillis();    //获取结束时间
            DecimalFormat df=new DecimalFormat("0.0000");//设置保留位数
            String time = df.format((float) (endTime - startTime) / 1000);  //毫秒转秒


            /*
               1次会话
             */
//              HttpSession session = req.getSession();
//               //(单选框如果选择了数据，且刷新了页面，保留之前单选内容)
//                session.setAttribute(fieldname,Fruit); //设置request域共享数据;
//               //设置request域共享数据;共享查出的数据;
//                session.setAttribute("Case",search);
//                //设置request域共享数据;(搜索框如果填写数据刷新了,保留之前搜索框输入的内容)
//                session.setAttribute("Case_name",case_name);
//                session.setAttribute("time",time);
//                resp.setStatus(302);
//                resp.sendRedirect(req.getContextPath()+"/admin.jsp");  //重定向


            /*
                1次请求
             */
            //(单选框如果选择了数据，且刷新了页面，保留之前单选内容)
            req.setAttribute(fieldname,Fruit); //设置request域共享数据;
            //设置request域共享数据;共享查出的数据;
            req.setAttribute("Case",search);
            //设置request域共享数据;(搜索框如果填写数据刷新了,保留之前搜索框输入的内容)
            req.setAttribute("Case_name",case_name);
            req.setAttribute("time",time);
            //获取转发器;转发
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(req.getContextPath() + "/admin.jsp");
            requestDispatcher.forward(req,resp); //转发的时候，顺带将response和request也转发过去;
        }


    }
}
