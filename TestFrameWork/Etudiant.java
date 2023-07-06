package model;

import monAnnotation.*;
import vue.ModelView;
import java.util.*;

@Scope
public class Etudiant
{
    String name;
    String poste;
    int salaire;
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

    public void settest(int test)
    {
        this.test=test;
    }
    public int gettest()
    {
        return this.test;
    }

    public Etudiant()
    {
        
    }

    @Model(url="/gouterDuJour")
    public int getGouter()
    {
        int result = this.getsalaire()/30;
        return result;
    }

    @Model(url="/formulaire_etu")
    public ModelView getCoordonnees()
    {
        this.settest(this.gettest()+1);
        ModelView mv=new ModelView();
        mv.setview("Valider.jsp");
        ArrayList<Etudiant> olona=new ArrayList<Etudiant>();
        Etudiant user=new Etudiant();
        user.settest(this.gettest());
        olona.add(user);
        mv.addItem("Liste_etudiant",olona);
        return mv;
    }
}