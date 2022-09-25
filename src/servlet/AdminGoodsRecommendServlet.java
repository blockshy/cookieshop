package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.GoodsService;

@WebServlet(name = "/admin/goods_recommend",urlPatterns = "/admin/goods_recommend")
public class AdminGoodsRecommendServlet extends HttpServlet {

    private final GoodsService gService;

    public AdminGoodsRecommendServlet() {
        gService = new GoodsService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String method = request.getParameter("method");
        int typeTarget = Integer.parseInt(request.getParameter("typeTarget"));
        if(method.equals("add"))
            gService.addRecommend(id, typeTarget);
        else
            gService.removeRecommend(id, typeTarget);
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
