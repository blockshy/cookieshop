//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebFilter(
        filterName = "AdminFilter",
        urlPatterns = {"/admin/*"}
)
public class AdminFilter implements Filter {
    public AdminFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse requestP = (HttpServletResponse)resp;
        User u = (User)request.getSession().getAttribute("user");
        if (u != null && u.isIsadmin()) {
            chain.doFilter(req, resp);
        } else {
            requestP.sendRedirect("../index.jsp");
        }
    }
    public void init(FilterConfig config){
    }
}
