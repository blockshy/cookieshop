package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

@WebServlet(name = "user_changeaddress",urlPatterns = "/user_changeaddress")
public class UserChangeAddressServlet extends HttpServlet {

    private final UserService uService;

    public UserChangeAddressServlet() {
        uService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        User loginUser = (User)request.getSession().getAttribute("user");
        try {
            BeanUtils.copyProperties(loginUser, request.getParameterMap());
        } catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        uService.updateUserAddress(loginUser);
        request.setAttribute("msg", "收货信息更新成功捏！");
        request.getRequestDispatcher("/user_center.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

}
