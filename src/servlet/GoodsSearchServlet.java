package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Goods;
import model.Page;
import service.GoodsService;

@WebServlet(name = "goods_search",urlPatterns = "/goods_search")
public class GoodsSearchServlet extends HttpServlet {

    private final GoodsService gService;

    public GoodsSearchServlet() {
        gService = new GoodsService();
    }

    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int pageNumber = 1;
        if(request.getParameter("pageNumber") != null)
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch(Exception ignored) { }
        if(pageNumber <= 0)
            pageNumber = 1;
        Page<Goods> p = gService.getSearchGoodsPage(keyword, pageNumber);
        if(p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else if(pageNumber >= p.getTotalPage() + 1)
            p = gService.getSearchGoodsPage(keyword, pageNumber);
        System.out.println(p.getList());
        request.setAttribute("p", p);
        request.setAttribute("keyword", URLEncoder.encode(keyword, "UTF-8"));
        request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
    }
}
