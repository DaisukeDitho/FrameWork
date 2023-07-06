package model;

import java.util.*;
import monAnnotation.*;
import vue.FileUpload;
import vue.ModelView;

public class Emp 
{
    String name;
    String poste;
    int salaire;
    String[]option;
    FileUpload sary;
    int test=0;

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

    public void setoption(String[]option)
    {
        this.option=option;
    }
    public String[] getoption()
    {
        return this.option;
    }

    public void setsary(FileUpload sary)
    {
        this.sary=sary;
    }
    public FileUpload getsary()
    {
        return this.sary;
    }

    public Emp(String name)
    {
        setname(name);
    }
    public Emp()
    {

    }

    public void settest(int test)
    {
        this.test=test;
    }
    public int gettest()
    {
        return this.test;
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
    public void getCoordonneess()
    {
        ModelView mmv = new ModelView();
        System.out.println(this.getname()+","+this.getposte()+","+this.getsalaire());
    }

    @Model(url="/load_choice")
    public ModelView load_choice()
    {
        ModelView mv = new ModelView();
        mv.setview("Choix.jsp");
        return mv;
    }

    @Model(url="/choice")
    public ModelView getChoix()
    {
        ModelView mv=new ModelView();
        mv.setview("TonChoix.jsp");
        ArrayList<Emp> mpiasa=new ArrayList<Emp>();
        Emp emp=new Emp();
        emp.setoption(this.getoption());
        mpiasa.add(emp);
        mv.addItem("Liste_personne",mpiasa);
        System.out.println("mnmmn");
        return mv;
    }

    @Model(url="/load_upload")
    public ModelView load_upload()
    {
        ModelView mv=new ModelView();
        mv.setview("Upload.jsp");
        return mv;
    }

    @Model(url="/upload")
    public ModelView getupload()
    {
        ModelView mv=new ModelView();
        mv.setview("Sary.jsp");
        ArrayList<Emp> olona=new ArrayList<Emp>();
        Emp emp=new Emp();
        emp.setsary(this.getsary());
        olona.add(emp);
        mv.addItem("Liste_personne",olona);
        return mv;
    }

    @Model(url="/formulaire_emp")
    public ModelView getCoordonnees()
    {
        this.settest(this.gettest()+1);
        ModelView mv=new ModelView();
        mv.setview("Valider_emp.jsp");
        ArrayList<Emp> olona=new ArrayList<Emp>();
        Emp user=new Emp();
        user.settest(this.gettest());
        olona.add(user);
        mv.addItem("Liste_emp",olona);
        return mv;
    }
}