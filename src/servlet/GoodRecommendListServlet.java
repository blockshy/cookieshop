package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Goods;
import model.Page;
import service.GoodsService;

@WebServlet(name = "goodsrecommend_list",urlPatterns = "/goodsrecommend_list")
public class GoodRecommendListServlet extends HttpServlet {

    private final GoodsService gService;

    public GoodRecommendListServlet() {
        gService = new GoodsService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int type = Integer.parseInt(request.getParameter("type"));
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
        } else if(pageNumber >= p.getTotalPage() + 1)
            p = gService.getGoodsRecommendPage(type, p.getTotalPage());
        request.setAttribute("p", p);
        request.setAttribute("t", type);
        request.getRequestDispatcher("goodsrecommend_list.jsp").forward(request, response);
    }

}
