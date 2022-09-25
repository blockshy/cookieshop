package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

@WebServlet(name = "/admin/user_add",urlPatterns = "/admin/user_add")
public class AdminUserAddServlet extends HttpServlet {

    private final UserService uService;

    public AdminUserAddServlet() {
        uService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.copyProperties(user, request.getParameterMap());
        }
        catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if(uService.register(user)) {
            request.setAttribute("msg", "客户添加成功！");
            request.getRequestDispatcher("/admin/user_list").forward(request, response);
        } else {
            request.setAttribute("failMsg", "用户名或邮箱重复，请重新填写！");
            request.setAttribute("u", user);
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }
    }

}
