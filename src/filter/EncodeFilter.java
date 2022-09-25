package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 创建EncodingFilter类用于统一全站的编码，以防止出现乱码的情况。
 */

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodeFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
    /*
      本类通过注解的urlPatterns属性值为 /* 可以知道，本项目所有的请求都会经过EncodingFilter类，从而实现全站编码统一。
     */
}
