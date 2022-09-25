package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Page;
import model.User;
import service.UserService;

@WebServlet(name = "/admin/user_list",urlPatterns = "/admin/user_list")
public class AdminUserListServlet extends HttpServlet {

    private final UserService uService;

    public AdminUserListServlet() {
        uService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int pageNumber = 1;
        if(request.getParameter("pageNumber") != null)
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch(Exception ignored) { }
        if(pageNumber <= 0)
            pageNumber = 1;
        Page<User> p = uService.getUserPage(pageNumber);
        if(p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else
        if(pageNumber >= p.getTotalPage() + 1)
            p = uService.getUserPage(pageNumber);
        request.setAttribute("p", p);
        request.getRequestDispatcher("/admin/user_list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        doGet(req, resp);
    }

}
