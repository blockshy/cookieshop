package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.GoodsService;

@WebServlet(name = "/admin/goods_delete",urlPatterns = "/admin/goods_delete")
public class AdminGoodsDeleteServlet extends HttpServlet {

    private final GoodsService gService;

    public AdminGoodsDeleteServlet() {
        gService = new GoodsService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        gService.delete(id);
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
