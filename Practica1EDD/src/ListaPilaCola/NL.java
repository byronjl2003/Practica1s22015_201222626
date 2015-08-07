/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaPilaCola;


import Objetos.Objeto;

/**
 *
 * @author byron
 */
public class NL {
    private Objeto objeto;
    private NL next,back;
    private int idnode;
    
    public NL(Objeto oj,int id)
    {
        this.objeto = oj;
        this.next = null;
        this.back = null;
        this.idnode = id;
        
    }
    
    
    public String ToStringEncabezado()
    {
        return "Nodo"+idnode+"[label=\"TIPO: "+this.Ttostring()+"\\"+"nNOMBRE: "+this.getObjeto().getNombre()+"\"];";
    }
    
    public String ToString()
    {
        return "Nodo"+idnode;
    }
    
    public String Ttostring()
    {
       switch(this.getObjeto().getId())
       {
           case 0:
               return"Suelo";
               
           case 1:
               return "Pared";
           case 2:
               return "Goomba";
           case 3:
               return "Koopa";
           case 4:
               return "Ficha";
           case 5:
               return "Hongo";
           case 6:
               return "Mario";
           case 7:
               return "Castillo";
           default:
               return "";
               
               
       }
    }

    /**
     * @return the objeto
     */
    public Objeto getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the next
     */
    public NL getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(NL next) {
        this.next = next;
    }

    /**
     * @return the back
     */
    public NL getBack() {
        return back;
    }

    /**
     * @param back the back to set
     */
    public void setBack(NL back) {
        this.back = back;
    }
    
    
}
