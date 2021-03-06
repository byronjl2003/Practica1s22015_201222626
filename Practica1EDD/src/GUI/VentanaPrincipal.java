/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Imagenes.imagen;
import ListaPilaCola.Lista;
import Objetos.Castillo;
import Objetos.Ficha;
import Objetos.Goomba;
import Objetos.Hongo;
import Objetos.Koopa;
import Objetos.Mario;
import Objetos.Pared;
import Objetos.Suelo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author byron
 */
public class VentanaPrincipal extends JPanel implements ActionListener {
    
    JButton btncargar,btnmaker,btngrafica;
    JFrame vent;
    imagen imgs;
    JPanel jpcarga,jpcarga1;
    Maker maker;
    JScrollPane scroll;
    JRadioButton radiopila,radiocola;
    ButtonGroup bg;
    //-------elementos para el panel de carga
  
    PanelLista panellista;
    //elementos para el panel maker
    Lista lista;
    public VentanaPrincipal()
    {
        
       
       // imagen imagenn = new imagen();
        lista = new Lista();
        //lista.Add(new Pared("pared",imagenn.pared()));
        panellista = new PanelLista(lista);
        imgs = new imagen();
        vent= new JFrame("MARIO MAKER!");
        vent.setSize(1100,700);
        vent.getContentPane().setLayout(null);
        vent.setResizable(true
        
        
        );
        //vent.setLocationRelativeTo(null);
        vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1100,700);
        this.setLayout(null);
        
        
        btncargar = new JButton("CARGAR");
        btncargar.addActionListener(this);
        btncargar.setBounds(450,10,90,25);
        //btncargar.addActionListener(this);
        this.add(btncargar);
        btnmaker = new JButton("MAKER");
        btnmaker.addActionListener(this);
        btnmaker.setBounds(550,10, 90,25);
        //btnmaker.addActionListener(this);
        this.add(btnmaker);
        //this.btngrafica = new JButton("Graficar");
        //btnmaker.addActionListener(this);
        //btnmaker.setBounds(660,10, 90,25);
        CrearPanelCarga();
        CrearPanelMaker();
        vent.add(this);
        vent.setVisible(true);
        
    }
    
    
    void CrearPanelCarga()
    {
        
        //System.out.println("EN CrearPanelCarga");
        jpcarga = new JPanel();
        jpcarga.setLayout(null);
        jpcarga.setBounds(100,50, 800, 600);
        jpcarga1 = new JPanel();
        jpcarga1.setBounds(0, 0, 500, 550);
        
        jpcarga1.setLayout(new GridLayout(4,2));
        
        for(int i=0;i<8;i++)
        {
            Panelito pan = new Panelito(this.imgs.general(i),i,lista,panellista); 
            pan.setBackground(Color.red);
            jpcarga1.add(pan);
        }
        jpcarga.add(jpcarga1);
        panellista.scroll.setBounds(500, 0, 250, 600);
        jpcarga.add(panellista.scroll);
        this.btngrafica = new JButton("Graficar");
        this.btngrafica.addActionListener(this);
        bg = new ButtonGroup();
        this.radiocola = new JRadioButton("COLA");
        //this.radiocola.addChangeListener(this);
        this.radiocola.addActionListener(this);
        this.radiopila = new JRadioButton("PILA");
        //this.radiopila.addChangeListener(this);
        this.radiopila.addActionListener(this);
        this.bg.add(radiocola);
        this.bg.add(radiopila);
        this.btngrafica.setBounds(50, 560, 100, 35);
        this.jpcarga.add(this.btngrafica);
        this.radiocola.setBounds(180, 560, 60, 35);
        this.radiopila.setBounds(250, 560, 60, 35);
        this.jpcarga.add(this.radiocola);
        this.jpcarga.add(this.radiopila);
        this.jpcarga.setVisible(false);
        this.add(jpcarga);
        
       
       
        
    }
    void CrearPanelMaker()
    {
        maker  = new Maker(this.lista);
        Dimension dim = maker.getSize();
        //maker.scroll.setBounds(120,100,dim.width,dim.height);
        maker.General.setBounds(120,100,maker.General.getWidth(),maker.General.getHeight());
        this.add(maker.General);
        maker.General.setVisible(false);
        //this.add(btn);
        
    }
    
    
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Dimension d = getSize();
        g.setColor(Color.red);
        setOpaque(false);

        g.drawImage(imgs.fondov(), 0, 0, d.width, d.height, this);
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btncargar)
        {
            this.jpcarga.setVisible(true);
            this.maker.General.setVisible(false);
            this.panellista.posy=0;
            this.panellista.llenar();
        }
        else if(e.getSource()==this.btnmaker)
        {
            this.jpcarga.setVisible(false);
            this.maker.General.setVisible(true);
            this.maker.refreshActual();
            this.maker.repaint();
            this.maker.areatxt.setText(this.lista.Restantes().toString());
        }
        else if(e.getSource()==this.btngrafica)
        {
            //System.out.println("CLICK EN GRAFICAR!");
            this.lista.Graficar("Lista");
        }
        else if(e.getSource()==this.radiocola)
        {
            //System.out.println("EN EL RADIO COLA");
            this.lista.setCola(true);
            this.lista.setPila(false);
            this.maker.refreshActual();
        }
        else if(e.getSource()==this.radiopila)
        {
           // System.out.println("EN EL RADIO PILA");
            this.lista.setCola(false);
            this.lista.setPila(true);
            this.maker.refreshActual();
        }
    }

    
    
    private class Panelito extends JPanel implements ActionListener
    {
        JPanel img;
        JButton btnadd;
        JTextField txt;
        int id;
        Lista list;
        Image imgg;
        PanelLista panellista;
        public Panelito(Image imggs,int id,Lista l,PanelLista pa)
        {
            this.panellista = pa;
            this.imgg = imggs;
            this.list = l;
            this.setLayout(null);
            this.id = id;
            img = new JPanel()
            {
                public void paintComponent(Graphics g)
                {
                   
                    super.paintComponent(g);
                    Dimension d = getSize();
                    setOpaque(false);
                    g.drawImage(imgg,0,0,d.width,d.height,this);
                }
 
 
            };
            img.setBounds(10, 10, 50, 50);
            btnadd = new JButton("+");
            btnadd.setBounds(90, 15, 80, 20);
            btnadd.addActionListener(this);
            txt = new JTextField();
            txt.setBounds(90, 40, 100, 20);
            this.add(img);
            this.add(btnadd);
            this.add(txt);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==this.btnadd)
            {
                switch(this.id)
                {
                    case 0:
                    {this.list.Add(new Suelo(this.txt.getText(),imgg) );
                        break;}
                    case 1:
                    {this.list.Add(new Pared(this.txt.getText(),imgg) );
                        break;}
                    case 2:
                    {this.list.Add(new Goomba(this.txt.getText(),imgg) );
                        break;}
                    case 3:
                    {this.list.Add(new Koopa(this.txt.getText(),imgg) );
                        break;}
                    case 4:
                    {this.list.Add(new Ficha(this.txt.getText(),imgg) );
                        break;}
                    case 5:
                    {this.list.Add(new Hongo(this.txt.getText(),imgg) );
                        break;}
                    case 6:
                    {this.list.Add(new Mario(this.txt.getText(),imgg) );
                        break;}
                    case 7:
                    {this.list.Add(new Castillo(this.txt.getText(),imgg) );
                        break;}
                    
                }
                panellista.llenar();
                panellista.posy = 0;
                panellista.repaint();
                
            }
                
            }
    }
            
   
    
}
