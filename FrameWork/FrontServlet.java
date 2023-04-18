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
            out.print("vghk");
            String url=response.encodeRedirectURL(request.getRequestURL().toString());
            String[] part=url.split("FrontServlet");
            if(1<part.length)
            {
                Set<String> allKey = mappingUrls.keySet();
                for(String oneKey : allKey)
                {
                    if(part[1].equals(oneKey))
                    {
                        Mapping m = (Mapping)mappingUrls.get(oneKey);
                        String classe = m.getclassName();
                        String method = m.getmethod();
                        ModelView mv=null;
                        try
                        {
                            T objet = instantiate(classe);
                            Method fonction = objet.getClass().getMethod(method);
                            mv = (ModelView)fonction.invoke(objet,(Object[]) null);
                            HashMap <String,Object> data = mv.getdata();
                            Set<String> keyData = data.keySet();
                            for(String keyObject : keyData)
                            {
                                T object = (T)data.get(keyObject);
                                request.setAttribute(keyObject,object);
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace(out);
                        }

                        RequestDispatcher redirect = request.getRequestDispatcher("/"+mv.getview());
                        redirect.forward(request,response);
                        return;
                    }
                }
            }
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
