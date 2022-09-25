package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.GoodsService;

@WebServlet(name = "/admin/goods_edit",urlPatterns = "/admin/goods_edit")
public class AdminGoodsEditServlet extends HttpServlet {

    private final GoodsService gService;

    public AdminGoodsEditServlet() {
        gService = new GoodsService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
label0:{
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> list = upload.parseRequest(request);
                Goods g = new Goods();
                int pageNumber = 1;
                int type = 0;
                Iterator<FileItem> iterator = list.iterator();
                do {
                    if(iterator.hasNext()) {
                        FileItem item = iterator.next();
                        if(item.isFormField()) {
                            String s = item.getFieldName();
                            byte byte0 = -1;
                            switch(s.hashCode()) {
                            case 3355: 
                                if(s.equals("id"))
                                    byte0 = 0;
                                break;

                            case 3373707: 
                                if(s.equals("name"))
                                    byte0 = 1;
                                break;

                            case 106934601: 
                                if(s.equals("price"))
                                    byte0 = 2;
                                break;

                            case 100361836: 
                                if(s.equals("intro"))
                                    byte0 = 3;
                                break;

                            case 94852023: 
                                if(s.equals("cover"))
                                    byte0 = 4;
                                break;

                            case -1185250762: 
                                if(s.equals("image1"))
                                    byte0 = 5;
                                break;

                            case -1185250761: 
                                if(s.equals("image2"))
                                    byte0 = 6;
                                break;

                            case 109770518: 
                                if(s.equals("stock"))
                                    byte0 = 7;
                                break;

                            case -858802731: 
                                if(s.equals("typeid"))
                                    byte0 = 8;
                                break;

                            case 1144767160: 
                                if(s.equals("pageNumber"))
                                    byte0 = 9;
                                break;

                            case 3575610: 
                                if(s.equals("type"))
                                    byte0 = 10;
                                break;
                            }
                            switch(byte0) {
                            case 0: // '\0'
                                g.setId(Integer.parseInt(item.getString("utf-8")));
                                break;

                            case 1: // '\001'
                                g.setName(item.getString("utf-8"));
                                break;

                            case 2: // '\002'
                                g.setPrice(Float.parseFloat(item.getString("utf-8")));
                                break;

                            case 3: // '\003'
                                g.setIntro(item.getString("utf-8"));
                                break;

                            case 4: // '\004'
                                g.setCover(item.getString("utf-8"));
                                break;

                            case 5: // '\005'
                                g.setImage1(item.getString("utf-8"));
                                break;

                            case 6: // '\006'
                                g.setImage2(item.getString("utf-8"));
                                break;

                            case 7: // '\007'
                                g.setStock(Integer.parseInt(item.getString("utf-8")));
                                break;

                            case 8: // '\b'
                                g.setTypeId(Integer.parseInt(item.getString("utf-8")));
                                break;

                            case 9: // '\t'
                                pageNumber = Integer.parseInt(item.getString("utf-8"));
                                break;

                            case 10: // '\n'
                                type = Integer.parseInt(item.getString("utf-8"));
                                break;
                            }
                        } else
                        if(item.getInputStream().available() > 0) {
                            String fileName = item.getName();
                            fileName = fileName.substring(fileName.lastIndexOf("."));
                            fileName = "/" + (new Date()).getTime() + fileName;
                            String path = getServletContext().getRealPath("/picture") + fileName;
                            InputStream in = item.getInputStream();
                            FileOutputStream out = new FileOutputStream(path);
                            byte[] buffer = new byte[1024];
                            while (in.read(buffer) > 0) {
                                out.write(buffer);
                            }

                            in.close();
                            out.close();
                            item.delete();
                            String s1 = item.getFieldName();
                            byte byte1 = -1;
                            switch(s1.hashCode()) {
                            case 94852023: 
                                if(s1.equals("cover"))
                                    byte1 = 0;
                                break;

                            case -1185250762: 
                                if(s1.equals("image1"))
                                    byte1 = 1;
                                break;

                            case -1185250761: 
                                if(s1.equals("image2"))
                                    byte1 = 2;
                                break;
                            }
                            switch(byte1) {
                            case 0: // '\0'
                                g.setCover("/picture" + fileName);
                                break;

                            case 1: // '\001'
                                g.setImage1("/picture" + fileName);
                                break;

                            case 2: // '\002'
                                g.setImage2("/picture" + fileName);
                                break;
                            }
                        }
                        continue;
                    }
                    gService.update(g);
                    request.getRequestDispatcher("/admin/goods_list?pageNumber=" + pageNumber + "&type=" + type).forward(request, response);
                    break label0;
                } while(true);
            }
            catch(FileUploadException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

}
