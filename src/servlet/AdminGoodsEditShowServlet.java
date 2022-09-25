package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.GoodsService;

@WebServlet(name = "/admin/goods_editshow",urlPatterns = "/admin/goods_editshow")
public class AdminGoodsEditShowServlet extends HttpServlet {

    private final GoodsService gService;

    public AdminGoodsEditShowServlet() {
        gService = new GoodsService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        model.Goods g = gService.getGoodsById(id);
        request.setAttribute("g", g);
        request.getRequestDispatcher("/admin/goods_edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
