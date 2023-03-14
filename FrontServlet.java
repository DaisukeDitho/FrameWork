package etu1971.framework.servlet;

import etu1971.framework.Mapping;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import fonction.*;
import model.*;

public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> mappingUrls;

    public void setmappingUrls(HashMap<String,Mapping> haha)
    {
        this.mappingUrls=haha;
    }
    public HashMap<String,Mapping> getmappingUrls()
    {
        return this.mappingUrls;
    }

    public void init()
    {
        String popo=getServletContext().getRealPath("/WEB-INF/classes");
        Path lololol=Paths.get(popo+"/model");
        setmappingUrls(Fonction.findAnnotatedMethod("C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/Construct-FrameWork/WEB-INF/classes/model")); 
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String[]tab=request.getRequestURL().toString().split("Construct-FrameWork");
            String result=tab[tab.length-1];

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListServiceController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + getmappingUrls().size() + "</h1>");
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
