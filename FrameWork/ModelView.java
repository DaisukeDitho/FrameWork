package vue;
import java.util.HashMap;

public class ModelView {
    String view;
    HashMap <String,Object> data;

    public void setview(String view)
    {
        this.view=view;
    } 
    public String getview()
    {
        return this.view;
    }
    public void setdata(HashMap data)
    {
        this.data=data;
    } 
    public HashMap <String,Object> getdata()
    {
        return this.data;
    }

    public void addItem(String key,Object value)
    {
        if (this.data == null) {
            this.data = new HashMap <String,Object>(); 
        }
        this.data.put(key,value);
    }

    public ModelView(String view)
    {
        this.setview(view);
        this.setdata(new HashMap <String,Object>());
    }

    public ModelView()
    {

    }

}
