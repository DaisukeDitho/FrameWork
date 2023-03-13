package etu1971.framework.servlet;

import etu1971.framework.Mapping;
import java.util.HashMap;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> mappingUrls;

    public void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String[]tab=request.getRequestURL().toString().split("/");
            String result=tab[tab.length-1];

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListServiceController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + result + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        processRequest(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        processRequest(request, response);
    }
}
