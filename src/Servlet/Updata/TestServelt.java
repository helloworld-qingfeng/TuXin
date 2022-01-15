package Servlet.Updata;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServelt extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");
//
//
//        String execl_dir = URLDecoder.decode(req.getParameter("execl_dir"), "UTF-8");
//        String[] execl_dirs = execl_dir.split("、");//获取文件数组;
//
//        String sheet_name = URLDecoder.decode(req.getParameter("sheet_name"), "UTF-8");
//        String[] sheet_names = sheet_name.split("、"); //获取月份数组;
//
//        for (String execl:execl_dirs) {
//            for (String sheet:sheet_names) {
//                System.out.println(execl);
//                System.out.println(sheet);
//            }
//        }
    }
}
