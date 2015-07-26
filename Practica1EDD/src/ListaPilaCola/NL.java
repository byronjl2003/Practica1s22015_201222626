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
    Objeto objeto;
    NL next,back;
    
    public NL(Objeto oj)
    {
        this.objeto = oj;
        this.next = null;
        this.back = null;
        
    }
    public String Tostring()
    {
       switch(this.objeto.id)
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
    
    
}
