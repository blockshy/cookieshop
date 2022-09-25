package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.User;
import service.UserService;

@WebServlet(name = "user_login",urlPatterns = "/user_login")
public class UserLoginServlet extends HttpServlet {

    private final UserService uService;

    public UserLoginServlet() {
        uService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String ue = request.getParameter("ue");
        String password = request.getParameter("password");
        User user = uService.login(ue, password);
        if(user == null) {
            request.setAttribute("failMsg", "用户名、邮箱或者密码错误捏！");
            request.getRequestDispatcher("/user_login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            if(user.isIsadmin()){
                request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("/user_center.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

}
