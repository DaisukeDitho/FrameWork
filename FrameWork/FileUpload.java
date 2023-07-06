package vue;

public class FileUpload
{
    String path;
    String name;
    byte[]binaire;

    public void setpath(String path)
    {
        this.path=path;
    }    
    public String getpath()
    {
        return this.path;
    }
    public void setname(String name)
    {
        this.name=name;
    }
    public String getname()
    {
        return this.name;
    }
    public void setbinaire(byte[]binaire)
    {
        this.binaire=binaire;
    }
    public byte[] getbinaire()
    {
        return this.binaire;
    }

    public FileUpload(String path,String name,byte[]binaire)
    {
        this.setpath(path);
        this.setname(name);
        this.setbinaire(binaire);
    }
    public FileUpload()
    {
        
    }
}