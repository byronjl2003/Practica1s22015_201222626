/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Imagenes.imagen;
import MDisp.MDisp;
import Objetos.Manejador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author byron
 */
public class Game extends JPanel {
    JFrame ventana;
    Manejador handler;
    MDisp matriz;
    Runnable runable;
    imagen imgs;
    public Game(MDisp mat)
    {
        imgs = new imagen();
        this.matriz = mat;
        handler = new Manejador(this.matriz,this.getGraphics(),this);
        ventana = new JFrame("JUEGUITO");
        ventana.setSize(1100,700);
        ventana.getContentPane().setLayout(null);
        this.setBounds(0, 0, 1100, 700);
        this.setLayout(null);
        this.ventana.add(this);
        new Thread(handler).start();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
     protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Dimension d = getSize();
        g.setColor(Color.red);
        setOpaque(false);
        

        g.drawImage(imgs.fondoj(), 0, 0, d.width, d.height, this);
     
    }
    
    
}
