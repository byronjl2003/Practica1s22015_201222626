/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import MDisp.MDisp;
import Objetos.Manejador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author byron
 */
public class Game extends JPanel {

    Manejador handler;
    MDisp matriz;
    public Game(MDisp mat)
    {
        this.matriz = mat;
        handler = new Manejador(this.matriz,this.getGraphics());
    }
     protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Dimension d = getSize();
        g.setColor(Color.red);
        setOpaque(false);

        //g.drawImage(imgs.fondov(), 0, 0, d.width, d.height, this);
     
    }
    
    
}
