package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

@WebServlet(name = "/admin/user_edit",urlPatterns = "/admin/user_edit")
public class AdminUserEditServlet extends HttpServlet {

    private final UserService uService;

    public AdminUserEditServlet() {
        uService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        User u = new User();
        try {
            BeanUtils.copyProperties(u, request.getParameterMap());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        uService.updateUserAddress(u);
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
    }

}
