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
public class Lista {
    
    private  NL primero, ultimo;
    private int elementos,suelos,paredes,goombas,koopas,fichas,hongos,personajes,castillos;
    
    public Lista()
    {
        this.elementos = 0;
        this.suelos = 0;
        this.paredes = 0;
        this.goombas = 0;
        this.koopas = 0;
        this.fichas = 0;
        this.hongos = 0;
        this.personajes = 0;
        this.castillos = 0;
        this.primero = null;
        this.ultimo = null;
    }
    private boolean Vacia()
    {
        return primero==null;
    }
    public void Add(Objeto obj)
    {
        if(Vacia())
        {
            this.primero = new NL(obj);
            this.ultimo = primero;
            this.elementos++;
            ContEspecial(obj.id);
        }
        else
        {
            if(obj.id==6)
            {
                if(this.personajes==0)
                {
                    
                }
            }
            else if(obj.id==7)
            {
                if(this.castillos==0)
                {
                    
                }
            }
            else
            {
                
            }
        
        }
            
    }
    private void ContEspecial(int id)
    {
        switch(id)
       {
           case 0:
               this.suelos++;
               
           case 1:
               this.paredes++;
           case 2:
               this.goombas++;
           case 3:
               this.koopas++;
           case 4:
               this.fichas++;
           case 5:
               this.hongos++;
           case 6:
               this.personajes++;
           case 7:
               this.castillos++;
           
               
               
       }
    }
    
}
