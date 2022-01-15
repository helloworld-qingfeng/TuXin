package Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/download")
public class downloadfileServlet extends HttpServlet {

    /*
       1.xls文件的采用浏览器的url传参，所以是get方式
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        //获取url尾部跟随的file_name的value;并且采用decode进行解码;由乱码解码至常规码;
        //这里只是文件名称的转义,并不是全路径;
        String file_name = req.getParameter("file_name");
        System.out.println(file_name);

        //获取全局域对象;
        ServletContext servletContext = this.getServletContext();

        //获取文件在服务器上的路径;
        String realPath = servletContext.getRealPath("/file_dir/"+file_name);
//        System.out.println(realPath);
        //加载到文件输入流，加载到内存;
        FileInputStream fileInputStream = new FileInputStream(realPath);

        //获取文件类型
        String mimeType = servletContext.getMimeType(realPath);
        //服务器写响应头的某个key和value;，响应数据类型是文件;
        resp.setHeader("context-type",mimeType);

        //文件名需要转义回去;即转为url乱码
        String filename = URLEncoder.encode(file_name, "utf-8");

        //响应头，告知浏览器,消息以附件方式传送;
        resp.setHeader("content-disposition","attachment;filename="+filename);

        ServletOutputStream sos = resp.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while((len = fileInputStream.read(buff)) != -1){
            sos.write(buff,0,len);
        }

        fileInputStream.close();

        File file = new File(realPath);   //判断文件是否存在,存在即删除;
        if(file.exists()){
            file.delete(); //删除文件;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
