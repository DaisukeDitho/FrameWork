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

    public Object convertParamValue(String paramValue,Class<?> paramType)
    {
        if(paramType == String.class)
        {
            return paramValue;
        }
        else if(paramType == int.class || paramType == Integer.class)
        {
            return Integer.parseInt(paramValue);
        }
        else if(paramType == boolean.class || paramType == Boolean.class)
        {
            return Boolean.parseBoolean(paramValue);
        }
        else if(paramType == double.class || paramType == Double.class)
        {
            return Double.parseDouble(paramValue);
        }
        return null;
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            out.print("vghk");
            String url=response.encodeRedirectURL(request.getRequestURL().toString());
            out.print(url);
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
                            Method[] methods=objet.getClass().getDeclaredMethods();
                            Method fonction = null;
                            
                            for(Method mt: methods)
                            {
                                if(mt.getName().equals(method))
                                {
                                    fonction=mt;
                                    break;
                                }
                            }
                            

                            Field[] field = objet.getClass().getDeclaredFields();
                            String[] attributs = new String[field.length];
                            for(int x=0;x<field.length;x++)
                            {
                                attributs[x] = field[x].getName();
                            }

                            Parameter[] param=fonction.getParameters();
                            ArrayList<Object> parameter=new ArrayList<>();
                            Enumeration<String> paramNames = request.getParameterNames();
                            while(paramNames.hasMoreElements())
                            {
                                String paramName = paramNames.nextElement();

                                for(int t=0;t<attributs.length;t++)
                                {
                                    if(attributs[t].equals(paramName))
                                    {
                                        String[] paramValues = request.getParameterValues(paramName);
                                        Method methody = objet.getClass().getMethod("set"+attributs[t],field[t].getType());
                                        Object paramValue = convertParamValue(paramValues[0],field[t].getType());
                                        methody.invoke(objet,paramValue);
                                    }
                                }
                                for(int y=0;y<param.length;y++)
                                {
                                    if(param[y].getName().equals(paramName))
                                    {
                                        String[] paramValues=request.getParameterValues(paramName);
                                        Object paramValue=convertParamValue(paramValues[0],param[y].getType());
                                        parameter.add(paramValue);
                                    }
                                }
                            }
                            Object[] paramfonction=parameter.toArray();
                            mv = (ModelView)fonction.invoke(objet,paramfonction);
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
