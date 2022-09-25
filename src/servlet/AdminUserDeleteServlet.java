package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.UserService;

@WebServlet(name = "/admin/user_delete",urlPatterns = "/admin/user_delete")
public class AdminUserDeleteServlet extends HttpServlet {

    private final UserService uService;

    public AdminUserDeleteServlet() {
        uService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isSuccess = uService.delete(id);
        if(isSuccess)
            request.setAttribute("msg", "客户删除成功");
        else
            request.setAttribute("failMsg", "客户有下的订单，请先删除该客户下的订单，再来删除客户！");
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
