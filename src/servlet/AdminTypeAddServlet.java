package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Type;
import service.TypeService;

@WebServlet(name = "/admin/type_add",urlPatterns = "/admin/type_add")
public class AdminTypeAddServlet extends HttpServlet {

    private final TypeService tService;

    public AdminTypeAddServlet() {
        tService = new TypeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String name = request.getParameter("name");
        tService.insert(new Type(name));
        request.getRequestDispatcher("/admin/type_list").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
