package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "user_logout",urlPatterns = "/user_logout")
public class UserLogoutServlet extends HttpServlet {

    public UserLogoutServlet() {
    }

    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/index");
    }
}
