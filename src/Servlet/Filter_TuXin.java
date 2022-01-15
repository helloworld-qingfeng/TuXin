package Servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class Filter_TuXin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        if(uri.contains("/CheckUserServelt")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/img/")||uri.contains("/index.jsp")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else if(uri.contains("/LiuCheng.jsp")){
            filterChain.doFilter(servletRequest,servletResponse);  //直接放行
            /*
                1.检测代码，现在不开启
             */
//            Object session = request.getSession().getAttribute("yuangong");
//
//            if(session != null){
//               request.getRequestDispatcher(request.getContextPath()+"/LiuCheng.jsp").forward(servletRequest,servletResponse);
//            }else {
//                response.setStatus(302);
//                response.sendRedirect(request.getContextPath()+"/index.jsp");
//            }

        }else if(uri.contains("/admin.jsp")){

            Object session = request.getSession().getAttribute("user");
            if(session != null){
                request.getRequestDispatcher(request.getContextPath()+"/admin.jsp").forward(servletRequest,servletResponse);
            }else {
                response.setStatus(302);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
