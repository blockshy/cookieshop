package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.TypeService;

@WebServlet(name = "/admin/type_delete",urlPatterns = "/admin/type_delete")
public class AdminTypeDeleteServlet extends HttpServlet {

    private final TypeService tService;

    public AdminTypeDeleteServlet() {
        tService = new TypeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isSuccess = tService.delete(id);
        if(isSuccess)
            request.setAttribute("msg", "删除成功");
        else
            request.setAttribute("failMsg", "类目下包含商品，无法直接删除类目！");
        request.getRequestDispatcher("/admin/type_list").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
