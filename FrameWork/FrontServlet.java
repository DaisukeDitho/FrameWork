package etu1971.framework.servlet;

import etu1971.framework.Mapping;
import java.util.*;
import java.lang.reflect.*;
import java.io.*;
import java.nio.file.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import fonction.*;
import vue.*;

@MultipartConfig
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
                            

                            fonction = objet.getClass().getMethod(method);
                            Parameter[] paramFonction=fonction.getParameters();
                            ArrayList<Object> listeParamFonction=new ArrayList<Object>();


                            Field[] field = objet.getClass().getDeclaredFields();
                            String[] attributs = new String[field.length];
                            for(int x=0;x<field.length;x++)
                            {
                                attributs[x] = field[x].getName();
                            }

                            Parameter[] param=fonction.getParameters();
                            ArrayList<Object> parameter=new ArrayList<>();
                            Enumeration<String> paramNames = request.getParameterNames();
                            try
                            {
                                Collection<Part>files=request.getParts();
                                for(int i=0;i<field.length;i++)
                                {
                                    Field f=field[i];
                                    if(f.getType()==vue.FileUpload.class)
                                    {
                                        Method mmethod=objet.getClass().getMethod("set"+attributs[i],field[i].getType());
                                        FileUpload o=this.upload(files,attributs[i]);
                                        mmethod.invoke(objet,o);
                                    }
                                }

                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                                Throwable cause=e.getCause();
                                cause.printStackTrace();
                                e.printStackTrace();
                            }
                            while(paramNames.hasMoreElements())
                            {
                                String paramName = paramNames.nextElement();

                                for(int t=0;t<attributs.length;t++)
                                {
                                    Method methody = objet.getClass().getMethod("set"+attributs[t],field[t].getType());
                                    if(attributs[t].equals(paramName))
                                    {   
                                        String[] paramValues = request.getParameterValues(paramName);
                                        if(field[t].getType().isArray())
                                        {
                                            Object para = convertParam(paramValues.length,field[t].getType(),paramValues);
                                            methody.invoke(objet,para);
                                        }
                                        else
                                        {
                                            Object paramValue = convertParamValue(paramValues[0],field[t].getType());
                                            methody.invoke(objet,paramValue);
                                        }
                                    } 
                                }
                                for(int uu=0;uu<paramFonction.length;uu++)
                                {
                                    if(paramFonction[uu].getName().equals(paramName))
                                    {
                                        String[] paramValues=request.getParameterValues(paramName);
                                        Object paramValue=convertParamValue(paramValues[0],paramFonction[uu].getType());
                                        listeParamFonction.add(paramValue);
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

                            Object[] paramFonctionObj=listeParamFonction.toArray();

                            mv = (ModelView)fonction.invoke(objet,paramFonctionObj);
                                System.out.println("aty");
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
                            Throwable cause=e.getCause();
                            cause.printStackTrace();
                            e.printStackTrace();
                        }

                        RequestDispatcher redirect = request.getRequestDispatcher("/"+mv.getview());
                        redirect.forward(request,response);
                        return;
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Object convertParam(int taille,Class<?> paramType,String[]val)
    {
        if(paramType==String[].class)
        {
            String[]n=new String[taille];
            for(int i=0;i<taille;i++)
            {
                n[i]=(String)convertParamValue(val[i],paramType.getComponentType());
            }
            return (Object)n;
        }
        else if(paramType==int[].class || paramType==Integer[].class)
        {
            int[]n=new int[taille];
            for(int i=0;i<taille;i++)
            {
                n[i]=(int)convertParamValue(val[i],paramType.getComponentType());
            }
            return (Object)n;
        }
        else if(paramType==boolean[].class || paramType==Boolean[].class)
        {
            boolean[]n=new boolean[taille];
            for(int i=0;i<taille;i++)
            {
                n[i]=(boolean)convertParamValue(val[i],paramType.getComponentType());
            }   
            return (Object)n;
        }
        else if(paramType==double[].class || paramType==Double[].class)
        {
            double[]n=new double[taille];
            for(int i=0;i<taille;i++)
            {
                n[i]=(double)convertParamValue(val[i],paramType.getComponentType());
            }
            return (Object)n;
        }
        return null;
    }

    public FileUpload upload(Collection<Part> files,String nomattribut)throws Exception
    {
        String path=null;
        Part filepart=null;
        for(Part part : files)
        {
            if(part.getName().equals(nomattribut))
            {
                path=Paths.get(part.getSubmittedFileName()).toString();
                filepart=part;
                break;
            }
        }
        try(InputStream io=filepart.getInputStream())
        {
            ByteArrayOutputStream buffers=new ByteArrayOutputStream();
            byte[]buffer=new byte[(int)filepart.getSize()];
            int read;
            while((read=io.read(buffer,0,buffer.length))!=-1)
            {
                buffers.write(buffer,0,read);
            }
            FileUpload file=new FileUpload(path,this.getFileName(filepart),buffers.toByteArray());
            return file;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    public String getFileName(Part part)
    {
        String contentDisposition=part.getHeader("content-disposition");
        String[] elements=contentDisposition.split(";");
        for(String element : elements)
        {
            if(element.trim().startsWith("filename"))
            {
                return element.substring(element.indexOf('=')+1).trim().replace("\"","");
            }
        }
        return null; 
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
