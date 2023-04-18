package model;

import java.util.*;
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

    public Emp(String name)
    {
        setname(name);
    }
    public Emp()
    {

    }

    @Model(url="/salaireJournalier")
    public ModelView getView()
    {
        ArrayList <Emp> mpiasa = new ArrayList<Emp>();
        Emp za = new Emp("Fiaro");
        mpiasa.add(za);
        Emp elah = new Emp("Antsa");
        mpiasa.add(elah);
        Emp tazika = new Emp("TojoKely");
        mpiasa.add(tazika);
        
        ModelView ta = new ModelView("emp.jsp");

        ta.addItem("liste",mpiasa);

        return ta;
    }

    @Model(url="/load_form")
    public ModelView load_form()
    {
        ModelView mmv = new ModelView();
        mmv.setview("Save.jsp");
        return mmv;
    }

    @Model(url="/formulaire")
    public void getCoordonnees()
    {
        ModelView mmv = new ModelView();
        System.out.println(this.getname()+","+this.getposte()+","+this.getsalaire());
    }
}