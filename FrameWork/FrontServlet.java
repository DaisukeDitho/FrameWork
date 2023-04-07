package etu1971.framework.servlet;

import etu1971.framework.Mapping;
import java.util.*;
import java.lang.reflect.*;
import java.io.*;
import java.nio.file.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import fonction.*;
import vue.*;

public class FrontServlet<T> extends HttpServlet {
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

    public static <T> T instantiate(String className) throws Exception
    {
        Class<T> clazz = (Class<T>) Class.forName(className);
        Constructor<T> constructor = clazz.getConstructor();
        T instance = constructor.newInstance();
        return instance;
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            String url=response.encodeRedirectURL(request.getRequestURL().toString());
                out.print(url);

                        return;
                    
                
            
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
