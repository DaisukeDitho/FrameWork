package etu1971.framework;

public class Mapping 
{
    String className;
    String method;

    public void setclassName(String className)
    {
        this.className=className;
    }
    public String getclassName()
    {
        return this.className;
    }

    public void setmethod(String method)
    {
        this.method=method;
    }
    public String getmethod()
    {
        return this.method;
    }


    public Mapping(String className,String method)
    {
        this.setclassName(className);
        this.setmethod(method);
    }
}