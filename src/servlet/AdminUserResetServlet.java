package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

@WebServlet(name = "/admin/user_reset",urlPatterns = "/admin/user_reset")
public class AdminUserResetServlet extends HttpServlet {

    private final UserService uService;

    public AdminUserResetServlet() {
        uService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        User u = new User();
        try {
            BeanUtils.copyProperties(u, request.getParameterMap());
        }
        catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        uService.updatePwd(u);
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
    }

}
