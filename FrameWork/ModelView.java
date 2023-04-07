package vue;

public class ModelView {
    String view;

    public void setview(String view)
    {
        this.view=view;
    } 
    public String getview()
    {
        return this.view;
    }

    public ModelView(String view)
    {
        this.setview(view);
    }

}
