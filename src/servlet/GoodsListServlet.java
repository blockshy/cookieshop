package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Goods;
import model.Page;
import model.Type;
import service.GoodsService;
import service.TypeService;


@WebServlet(name = "goods_list",urlPatterns = "/goods_list")
public class GoodsListServlet extends HttpServlet {

    private final GoodsService gService;
    private final TypeService tService;

    public GoodsListServlet() {
        gService = new GoodsService();
        tService = new TypeService();
    }

    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = 0;
        if(request.getParameter("typeid") != null)
            id = Integer.parseInt(request.getParameter("typeid"));
        int pageNumber = 1;
        if(request.getParameter("pageNumber") != null)
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch(Exception ignored) { }
        Type t = null;
        if(id != 0)
            t = tService.selectTypeNameByID(id);
        request.setAttribute("t", t);
        if(pageNumber <= 0)
            pageNumber = 1;
        Page<Goods> p = gService.selectPageByTypeID(id, pageNumber);
        if(p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else if(pageNumber >= p.getTotalPage() + 1)
            p = gService.selectPageByTypeID(id, p.getTotalPage());
        request.setAttribute("p", p);
        request.setAttribute("id", String.valueOf(id));
        request.getRequestDispatcher("/goods_list.jsp").forward(request, response);
    }

}
