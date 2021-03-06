package MDisp;

import Objetos.Objeto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author byron
 */
public class NM extends JPanel{
    
    public NM Next, Back;
    public Objeto Dato;
    public int Dimension;
    public NM(Objeto dato,int dimension)
    {
        this.Dato = dato;
        this.Next = this.Back = null;
        this.Dimension = dimension;

    }
    
    protected void paintComponent(Graphics g)
    {
       // System.out.println("va");
        super.paintComponent(g);
        this.setBackground(Color.CYAN);
        Dimension d = getSize();
        g.setColor(Color.MAGENTA);
        setOpaque(false);
        if(this.Dato!=null)
        {
         //   System.out.println("DEBERIA DE PINTARLO");
            if(this.Dato.getImage()!=null)
            {
                
                g.drawImage(this.Dato.getImage(), 2, 2, d.width-2, d.height-2, this);
            }
        }
        else
        {
           // System.out.println("EL DATO ES NULO");
        }
        
        g.drawRect(0, 0, d.width-1, d.height-1);

       
        

        

    }
    
    public String ToString()
    {
        
        String resp = "";
        if(this.Dato==null)
        {
            resp = "VACIO";
        }
        else
        {
            
           switch(this.Dato.getId())
        {
               
            case 0:{
                    if(this.Dato==null)
                        resp="VACIO";
                    else
                        resp = "TIPO: Suelo \\nNombre:"+this.Dato.getNombre();
                    }
            case 1:{
                    if(this.Dato==null)
                        resp="VACIO";
                    else
                        resp = "TIPO: Pared \\nNombre:"+this.Dato.getNombre();
                    }
            case 2:{
                    if(this.Dato==null)
                        resp="VACIO";
                    else
                        resp = "TIPO: Goomba \\nNombre:"+this.Dato.getNombre();
                    }
            case 3:{
                    if(this.Dato==null)
                        resp="VACIO";
                    else
                        resp = "TIPO: Koopa \\nNombre:"+this.Dato.getNombre();
                    }
            case 4:{
                    if(this.Dato==null)
                        resp="VACIO";
                    else
                        resp = "TIPO: Ficha \\nNombre:"+this.Dato.getNombre();
                    }
            case 5:{
                    if(this.Dato==null)
                        resp="VACIO";
                    else
                        resp = "TIPO: Hongo \\nNombre:"+this.Dato.getNombre();
                    }
            case 6:{
                    if(this.Dato==null)
                        resp="VACIO";
                    else
                        resp = "TIPO: Personaje \\nNombre:"+this.Dato.getNombre();
                    }
            case 7:{
                    if(this.Dato==null)
                        resp="VACIO";
                    else
                        resp = "TIPO: Castillo \\nNombre:"+this.Dato.getNombre();
                    }
        } 
        }
        return resp;
        
    }
    

    
}
