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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author byron
 */
public class Game extends JPanel implements Runnable,ActionListener{
    JFrame ventana;
    Manejador handler;
    MDisp matriz,matrizBackup;
    //Runnable runable;
    imagen imgs;
    JButton btnplay,btngraficar,btnreinicio;
    Cronometro cronometro;    
    boolean play;
    Thread juego,crono;
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
                            //System.out.println("EN WL KEYPRESSED DEL GAME");
                            handler.oidomario(e);
			}
		});
		
        
        
        imgs = new imagen();
        this.matriz = mat;
        handler = new Manejador(this.matriz,this);
        ventana = new JFrame("JUEGUITO");
        int with = this.matriz.getLcolumnas().elementos*75;
        int heigthh =  ((this.matriz.getLcolumnas().ListaFilas.elementos-3)*75)+80;
        ventana.setSize(with,heigthh);
        ventana.getContentPane().setLayout(null);
        
        this.btnplay = new JButton("Pause");
        this.btnplay.addActionListener(this);
        this.btnplay.setBounds(10,30,75,30);
        this.ventana.add(this.btnplay);
        this.btnplay.setFocusable(false);
        
        
        this.btngraficar = new JButton("graficar");
        this.btngraficar.addActionListener(this);
        this.btngraficar.setBounds(95, 10, 75, 25);
        this.btngraficar.setFocusable(false);
        this.ventana.add(this.btngraficar);
        this.btnreinicio = new JButton("Reini");
        this.btnreinicio.addActionListener(this);
        this.btnreinicio.setBounds(95, 40, 75, 25);
        this.btnreinicio.setFocusable(false);
        this.ventana.add(this.btnreinicio);
        this.cronometro = new Cronometro();
        this.cronometro.setBounds(190, 10 , 100,50);
        this.cronometro.setFocusable(false);
        this.ventana.add(this.cronometro);
        
        setFocusable(true);
        this.setBounds(0,80, with,heigthh-80);
        this.setLayout(null);
        this.ventana.add(this);
        play = true;
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        juego = new Thread(this);
        juego.start();
        crono = new Thread(this.cronometro);
        setOpaque(false);
        
    }
     protected void paintComponent(Graphics g)
    {
        System.out.println("PAINTCOMPONENT");
        super.paintComponent(g);
        Dimension d = getSize();
        g.setColor(Color.red);
        
        g.drawImage(imgs.fondoj(), 0,0, d.width, d.height, this);
        this.handler.Pintar(g);
     
    }
    
     

    @Override
    public void run() {
        while(play)
        {
            
            //System.out.println("EN EL RUN!");
            this.repaint();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
   
    }
    
    private void Colisiones()
    {
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==this.btngraficar)
        {
            this.matriz.graficarMDispersa2();
        }
       
        
        
        
    }

    
    
    
}
