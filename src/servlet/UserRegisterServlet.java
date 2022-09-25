package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

@WebServlet(name = "user_register",urlPatterns = "/user_register")
public class UserRegisterServlet extends HttpServlet {

    private final UserService uService;

    public UserRegisterServlet() {
        uService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.copyProperties(user, request.getParameterMap());
        } catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if(uService.register(user)) {
            request.setAttribute("msg", "注册成功捏，请登录！");
            request.getRequestDispatcher("user_login.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "用户名或邮箱重复捏！");
            request.getRequestDispatcher("user_register.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

}

