package fonction;

import java.lang.annotation.*;
import java.util.Vector;
import java.util.*;
import java.io.File;
import java.lang.reflect.*;
import monAnnotation.*;
import java.util.HashMap;
import etu1971.framework.*;

public class Fonction{
    
    public static Vector<Class<?>> findClasses(String packageName)
    {
        Vector<Class<?>> classes = new Vector<Class<?>>();
        String[]yu=packageName.split("/");

        //String basePath = packageName.replace(".", "/");
        File directory = new File(packageName);
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile() && fileName.endsWith(".class")) {
                packageName=yu[yu.length-1];
                String className = packageName + "." + fileName.substring(0, fileName.length() - 6);
                //System.out.println("vita : "+className);
                try {
                    //System.out.println("voalohany : "+fileName);

                    Class<?> clazz = Class.forName(className);
                    classes.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    // Handle exception
                }
            } else if (file.isDirectory()) {
                Vector<Class<?>> subClasses = findClasses(packageName + "." + fileName);
                classes.addAll(subClasses);
            }
        }
        return classes;
    }

    public static HashMap<String,Mapping> findAnnotatedMethod(String packageName,HashMap<String,Object> instance) 
    {
        HashMap<String,Mapping> list = new HashMap<String,Mapping>();
        String[]result=new String[3];
        Vector<Class<?>> annotatedClasses = new Vector<Class<?>>();
        Vector<Class<?>> classes = findClasses(packageName);
        for (Class<?> clazz : classes) {
            Scope scopeAnnotation=clazz.getAnnotation(Scope.class);
            if(scopeAnnotation!=null)
            {
                instance.put(clazz.getName(),null);
            }
            Method[]allMeth=clazz.getDeclaredMethods();
            for (Method one : allMeth) {
                Annotation[]annot=one.getDeclaredAnnotations();
                for (Annotation aa : annot) {
                    Model anah = (Model) aa;
                    
                    result[0]=clazz.getName();
                    result[1]=one.getName();
                    result[2]=anah.url();
                    System.out.println("Classe : "+result[0]);
                        System.out.println("Fonction : "+result[1]);
                        System.out.println("URL : "+result[2]);
                        System.out.println("--------------------------------");

                        Mapping koko=new Mapping(result[0],result[1]);
                        list.put(result[2],koko);

                        System.out.println("isany : " + list.size());
                }
            }
        }

        return list;
    }
}   