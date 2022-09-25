package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Type;
import service.TypeService;
import java.util.*;

@WebServlet(name = "/admin/type_list",urlPatterns = "/admin/type_list")
public class AdminTypeListServlet extends HttpServlet {

    private final TypeService tService;

    public AdminTypeListServlet() {
        tService = new TypeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        List<Type> list = tService.GetAllType();
        request.setAttribute("list", list);
        getServletContext().removeAttribute("typeList");
        getServletContext().setAttribute("typeList", list);
        request.getRequestDispatcher("/admin/type_list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
