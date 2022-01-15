package Servlet.Login;

import dao.LoGin;
import javabean.User;
import service.Login.TuXin_Login;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CheckUserServelt")
public class CheckUserServelt extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /*
         1.登录查询页面;
       */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); //设置读取字符集;
        resp.setContentType("text/html;charset=utf-8"); //设置相应的数据是文本/html,字符集是utf-8;
        String username;
        String password;
        LoGin login = null;  //多态
        username = req.getParameter("username");
        password = req.getParameter("password");

        if(username.length() != 0 && password.length() != 0){
            //user数据库查询
            login=new TuXin_Login();  //多态
            List<User> searchuser = login.checkUser(username,password);

            //获取当前回话的seesion
            HttpSession session = null;
            session = req.getSession();

            //根据当前回话的seesion，生成对应的cookie;存活时间60*60*60
            Cookie cookie = login.makeCookie(req, session);
            resp.addCookie(cookie);//回写cookie;

            //判断是否是股东或者其他员工;（获取判断条件）
            String type = login.checkUserType(searchuser);

            if( searchuser != null){
                if("1".equals(type)){
                    //返回正确页面;并且是股东的：
                    //1.设置seesion数据域;名称是user;
                    session.setAttribute("user",searchuser);
                    req.getRequestDispatcher(req.getContextPath()+"/admin.jsp").forward(req,resp);
                }else if("2".equals(type) ){
                    //返回正确页面;并且是员工的：
                     //1.设置seesion数据域;名称是user;
                    session.setAttribute("yuangong",searchuser);
                    req.getRequestDispatcher(req.getContextPath()+"/LiuCheng.jsp").forward(req,resp);
                }else{
                    //返回错误页面;
                    resp.setStatus(302);
                    resp.sendRedirect(req.getContextPath()+"/error_login.jsp");
                }
            }else{
                //返回错误页面;
                resp.setStatus(302);
                resp.sendRedirect(req.getContextPath()+"/error_login.jsp");
            }


        }else {
            //密码或者用户如果为空，返回这个页面;
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }
    }
}
