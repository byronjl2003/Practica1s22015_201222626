/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Imagenes.imagen;
import ListaPilaCola.Lista;
import MDisp.MDisp;
import Objetos.Manejador;
import Objetos.Mario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author byron
 */
public class Game extends JPanel implements Runnable,ActionListener{
    JFrame ventana;
    Manejador handler;
    MDisp matriz,matrizBackup;
    //Lista lviv;
    //Runnable runable;
    imagen imgs;
    JButton btnplay,btngraficar,btnreinicio;
    JLabel lblvidas,lblpuntos;
    Cronometro cronometro;    
    public boolean play,finish;
    Thread juego,crono;
    Mario mario;
    Image imagenfondo;
    public Game(MDisp mat)
    {
         imgs = new imagen();
         this.imagenfondo = imgs.fondoj();
        this.finish = false;
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
		
        
        
       
        this.matriz = mat;
        //this.lviv = lvivientes;
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
        this.cronometro.setBounds(190, 0 , 100,40);
        this.cronometro.setFocusable(false);
        this.ventana.add(this.cronometro);
        this.lblpuntos = new JLabel();
        this.lblpuntos.setBounds(190,50,100,30);
        this.lblpuntos.setFocusable(false);
        this.ventana.add(this.lblpuntos);
        this.lblvidas = new JLabel();
        this.lblvidas.setBounds(290, 50,100, 30);
        this.lblvidas.setFocusable(false);
        this.ventana.add(this.lblvidas);
        setFocusable(true);
        this.setBounds(0,80, with,heigthh-80);
        this.setLayout(null);
        this.ventana.add(this);
        play = true;
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        mario = (Mario)this.handler.Buscarmario();
        
        juego = new Thread(this);
        juego.start();
        crono = new Thread(this.cronometro);
        crono.start();
        setOpaque(false);
        
    }
     protected void paintComponent(Graphics g)
    {
       // System.out.println("PAINTCOMPONENT");
        super.paintComponent(g);
        Dimension d = getSize();
        g.setColor(Color.red);
        
        g.drawImage(this.imagenfondo, 0,0, d.width, d.height, this);
        this.handler.Pintar(g);
     
    }
    
     
    private void refrescarmario()
    {
        if(mario!=null)
        {
            this.lblpuntos.setText("PUNTOS:"+mario.puntos);
        this.lblvidas.setText("VIDAS:"+mario.vidas);
        }
        
    }
    @Override
    public void run() {
       while(!finish)
       {
          System.out.println(1);
        while(play)
        {
           System.out.println(2); 
            //System.out.println("EN EL RUN!");
            this.repaint();
            this.refrescarmario();
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
       }
       this.cronometro.stop();
       this.cronometro.finish = true;
       System.out.println("SALIO DEL JUEGO por QUe MUriO mARIO");
       this.refrescarmario();
       this.imagenfondo = this.imgs.gameover();
       this.repaint();
       //this.handler.Terminar();
        
        
   
    }
    
   

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==this.btngraficar)
        {
            this.matriz.graficarMDispersa2();
        }
        else if(e.getSource()==this.btnreinicio)
        {
            this.handler.Reiniciar();
            this.cronometro.reiniciar();
           // this.play = true;
        }
        else if(e.getSource()==this.btnplay)
        {
            if(play==true)
            {
                play = false;
                if(this.mario!=null)
                    this.mario.bloqueo = true;
                this.btnplay.setText("PLAY");
                this.cronometro.stop();
            }
            else
            {
                play = true;
                if(this.mario!=null)
                    this.mario.bloqueo = false;
                this.btnplay.setText("PAUSE");
                this.cronometro.play();
            }
        }
        
       
        
        
        
    }

    
    
    
}
