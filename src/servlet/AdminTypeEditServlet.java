package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Type;
import org.apache.commons.beanutils.BeanUtils;
import service.TypeService;

@WebServlet(name = "/admin/type_edit",urlPatterns = "/admin/type_edit")
public class AdminTypeEditServlet extends HttpServlet {

    private final TypeService tService;

    public AdminTypeEditServlet() {
        tService = new TypeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Type t = new Type();
        try {
            BeanUtils.copyProperties(t, request.getParameterMap());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        tService.update(t);
        request.getRequestDispatcher("/admin/type_list").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
