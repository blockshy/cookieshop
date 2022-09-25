package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.UserService;

@WebServlet(name = "/admin/user_editshow",urlPatterns = "/admin/user_editshow")
public class AdminUserEditShowServlet extends HttpServlet {

    private final UserService uService;

    public AdminUserEditShowServlet() {
        uService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        model.User user = uService.selectById(id);
        request.setAttribute("u", user);
        request.getRequestDispatcher("/admin/user_edit.jsp").forward(request, response);
    }

}
