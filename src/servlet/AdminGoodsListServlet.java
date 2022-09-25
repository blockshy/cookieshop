package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Goods;
import model.Page;
import service.GoodsService;

@WebServlet(name = "/admin/goods_list",urlPatterns = "/admin/goods_list")
public class AdminGoodsListServlet extends HttpServlet {

    private final GoodsService gService;

    public AdminGoodsListServlet() {
        gService = new GoodsService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int type = 0;
        if(request.getParameter("type") != null)
            type = Integer.parseInt(request.getParameter("type"));
        int pageNumber = 1;
        if(request.getParameter("pageNumber") != null)
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch(Exception ignored) { }
        if(pageNumber <= 0)
            pageNumber = 1;
        Page<Goods> p = gService.getGoodsRecommendPage(type, pageNumber);
        if(p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else
        if(pageNumber >= p.getTotalPage() + 1)
            p = gService.getGoodsRecommendPage(type, pageNumber);
        request.setAttribute("p", p);
        request.setAttribute("type", type);
        request.getRequestDispatcher("/admin/goods_list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
