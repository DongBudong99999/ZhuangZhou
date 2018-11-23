package springboot_demo1.demo.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnicodeFilter implements Filter {

    @Override
    public void destroy() {


    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        if(arg0 instanceof HttpServletRequest){
            System.out.println("进入了过滤器");
            HttpServletRequest request = (HttpServletRequest)arg0;
            request.setCharacterEncoding("UTF-8"); //设置编码，防止后台得到中文乱码
        }
        if (arg1 instanceof HttpServletResponse){
            HttpServletResponse response = (HttpServletResponse)arg1;
            response.setCharacterEncoding("UTF-8");//设置编码，防止返回客户端乱码
        }
        arg2.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
