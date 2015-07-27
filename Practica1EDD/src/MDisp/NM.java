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
public class NM extends JPanel {
    
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
        super.paintComponent(g);
        Dimension d = getSize();
        g.setColor(Color.BLACK);
        setOpaque(false);
        if(this.Dato.getImage()!=null)
        {
            g.drawImage(this.Dato.getImage(), 2, 2, d.width-2, d.height-2, this);
        }
        g.drawRect(0, 0, d.width, d.height);

       
        

        

    }
    
    public String ToString()
    {
        if (Dimension == 1)
        {
            return "SATELITE" + Dato;
        }
        else if(Dimension==2)
        {
            return "AVION" + Dato;
        }
        else if (Dimension == 3)
        {
            return "BARCO" + Dato;
        }
        else
        {
            return "SUBMARINO" + Dato;
        }
    }
    

    
}
