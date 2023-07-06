package model;

import java.util.*;
import monAnnotation.*;
import vue.ModelView;

public class Emp 
{
    String name;
    String poste;
    int salaire;
    int id;

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
    public void setid(int id)
    {
        this.id=id;
    }
    public int getid()
    {
        return this.id;
    }

    public Emp(String name)
    {
        setname(name);
    }
    public Emp(String name,int id)
    {
        setname(name);
        setid(id);
    }
    public Emp()
    {

    }

    @Model(url="/salaireJournalier")
    public ModelView getView()
    {
        ArrayList <Emp> mpiasa = new ArrayList<Emp>();
        Emp za = new Emp("Fiaro",1);
        mpiasa.add(za);
        Emp elah = new Emp("Antsa",2);
        mpiasa.add(elah);
        Emp tazika = new Emp("TojoKely",3);
        mpiasa.add(tazika);
        
        //System.out.println("TAY MAFANA : "+mpiasa.size());
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

    @Model(url="/detail")
    public ModelView voir_detail(int id)
    {
        ModelView mv=new ModelView();
        mv.setview("Detail.jsp");
        ArrayList<Emp> mpiasa=new ArrayList<Emp>();
        Emp[] mpiasaTab=new Emp[3];
        Emp emp1=new Emp("Daisuke",1);
        mpiasaTab[0]=emp1;
        Emp emp2=new Emp("Baby",2);
        mpiasaTab[1]=emp2;
        Emp emp3=new Emp("ZandryKely",3);
        mpiasaTab[2]=emp3;

        mpiasa.add(mpiasaTab[id-1]);
        mv.addItem("Liste_Employee",mpiasa);
        return mv;
    }
}