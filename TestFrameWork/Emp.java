package model;

import monAnnotation.*;
import vue.ModelView;

public class Emp 
{
    String name;
    String poste;
    int salaire;

    public void setname(String name)
    {
        this.name=name;
    }
    public String getname()
    {
        return this.name;
    }

    public void setposte(String poste)
    {
        this.poste=poste;
    }
    public String getposte()
    {
        return this.poste;
    }

    public void setsalaire(int salaire)
    {
        this.salaire=salaire;
    }
    public int getsalaire()
    {
        return this.salaire;
    }

    public Emp()
    {
        
    }

    @Model(url="/salaireJournalier")
    public ModelView getView()
    {
        int result = this.getsalaire()/30;
        ModelView ta = new ModelView("emp.jsp");
        return ta;
    }
}