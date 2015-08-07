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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author byron
 */
public class Game extends JPanel implements Runnable{
    JFrame ventana;
    Manejador handler;
    MDisp matriz;
    Runnable runable;
    imagen imgs;
    boolean play;
    public Game(MDisp mat)
    {
        addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
                                
			}

			@Override
			public void keyPressed(KeyEvent e) {
                            handler.oidomario(e);
			}
		});
		setFocusable(true);
        imgs = new imagen();
        this.matriz = mat;
        handler = new Manejador(this.matriz,this);
        ventana = new JFrame("JUEGUITO");
        ventana.setSize(1100,700);
        ventana.getContentPane().setLayout(null);
        this.setBounds(0, 0, 1100, 700);
        this.setLayout(null);
        this.ventana.add(this);
        play = true;
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        new Thread(this).start();
    }
     protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Dimension d = getSize();
        g.setColor(Color.red);
        setOpaque(false);
        g.drawImage(imgs.fondoj(), 0, 0, d.width, d.height, this);
        this.handler.Pintar(g);
     
    }

    @Override
    public void run() {
        while(play)
        {
           // System.out.println("EN EL RUN!");
            this.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
   
    }

    
    
    
}
