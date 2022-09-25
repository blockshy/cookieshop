package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.User;
import service.UserService;

@WebServlet(name = "user_changePwd",urlPatterns = "/user_changePwd")
public class UserChangePwd extends HttpServlet {

    private final UserService uService;

    public UserChangePwd() {
        uService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String password = request.getParameter("password");
        String newPwd = request.getParameter("newPassword");
        User u = (User)request.getSession().getAttribute("user");
        if(password.equals(u.getPassword())) {
            u.setPassword(newPwd);
            uService.updatePwd(u);
            request.setAttribute("msg", "修改成功捏！");
        } else {
            request.setAttribute("failMsg", "修改失败捏，原密码错误！");
        }
        request.getRequestDispatcher("/user_center.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

}
