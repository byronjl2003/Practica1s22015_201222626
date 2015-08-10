/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Imagenes.imagen;
import ListaPilaCola.Lista;
import ListaPilaCola.NL;
import MDisp.MDisp;
import MDisp.NC;
import MDisp.NCasilla;
import MDisp.NF;
import MDisp.NM;
import Objetos.Mario;
import Objetos.Objeto;
import Objetos.Vacio;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//import javafx.beans.value.ObservableValue;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author byron
 */
public class Maker extends JPanel implements ChangeListener,ActionListener,MouseListener {
 JScrollPane scroll,scroll2;
 MDisp matriz;
 imagen imgs;
 JButton btnaddF,btnaddC,btnelim,btngraflista,btngrafmatrziz,btnplay;
 JTextField txtnum;
 JRadioButton radiofila,radiocolumna;
 ButtonGroup bg;
 JLabel lblobjeto;
 JTextArea areatxt;
 JPanel General;
 Lista lista;
 NL actual;
 Game game;
 MouseListener listener,listener2;

 public Maker(Lista l)
 {
    this.lista = l;
    
    System.out.println("CONSTRUCTOR MAKER"); 
    General = new JPanel(){
    public void paintComponent(Graphics g){
         super.paintComponent(g);
        //refreshActual();
          
             }
 
 
 };
    General.setLayout(null);
    General.setOpaque(true);
    General.setSize(800,600);
    this.btnaddC = new JButton("AddCol");
    this.btnaddC.addActionListener(this);
    this.btnaddF = new JButton("AddFil");
    this.btnaddF.addActionListener(this);
    
    bg = new ButtonGroup();
    this.radiocolumna = new JRadioButton("ElimCol");
    this.radiocolumna.addChangeListener(this);
    this.radiofila = new JRadioButton("ElimFil");
    this.radiofila.addChangeListener(this);
    bg.add(this.radiocolumna);
    bg.add(this.radiofila);
    this.lblobjeto = new JLabel();
    
    if(actual!=null)
    {
        Icon icono = new ImageIcon(actual.getObjeto().getImage());
        this.lblobjeto.setIcon(icono);
    }
    this.txtnum = new JTextField();
    this.btnelim = new JButton();
    this.btnelim.addActionListener(this);
    this.areatxt = new JTextArea();
    this.scroll2 = new JScrollPane(this.areatxt);
    this.setSize(4*75,2*75);
    this.setOpaque(true);
    imgs = new imagen();
    this.btngraflista = new JButton("Graf,Lista");
    this.btngraflista.addActionListener(this);
    this.btngrafmatrziz = new JButton("Graf,Matriz");
    this.btngrafmatrziz.addActionListener(this);
    this.btnplay = new JButton("PLAY");
    this.btnplay.addActionListener(this);
    //***Se empieza a colocar lo de arriba en el panel
        this.btnaddC.setBounds(10, 10, 100, 25);
        this.General.add(btnaddC);
        this.btnaddF.setBounds(120, 10, 100, 25);
        this.General.add(btnaddF);
        this.radiofila.setBounds(240, 6,100, 25);
        this.General.add(this.radiofila);
        this.radiocolumna.setBounds(240, 30, 100, 25);
        this.General.add(this.radiocolumna);
        this.txtnum.setBounds(240, 60, 50, 25);
        this.btnelim.setBounds(291, 60, 50, 25);
        this.General.add(this.txtnum);
        this.General.add(this.btnelim);
        this.lblobjeto.setBounds(350, 10, 75, 75);
        
        this.General.add(this.lblobjeto);
        this.scroll2.setBounds(410,10,100,75);
        this.General.add(this.scroll2);
        this.btnplay.setBounds(610 ,20,100 ,25);
        this.General.add(this.btnplay);
        this.btngraflista.setBounds(515, 15, 100, 30);
        this.General.add(this.btngraflista);
        this.btngrafmatrziz.setBounds(515, 50, 100, 30);
        this.General.add(this.btngrafmatrziz);
        this.lblobjeto.setTransferHandler(new ImageSelection(this.lista,this));
        listener = new MouseAdapter() 
        {
            public void mousePressed(MouseEvent me)
            {
                JComponent comp = (JComponent) me.getSource();
                TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, me, TransferHandler.COPY);
            }
         };
        
       
        
        
        this.lblobjeto.addMouseListener(listener);
        //this.radiocolumna.setBounds(null);
        
    //*********
    matriz = new MDisp();
    scroll = new JScrollPane(this);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.setLayout(null);
    this.setPreferredSize(new Dimension(4*75,2*75));
    scroll.setViewportView(this);
    for(int i=0;i<2;i++)
    {
        for(int j=0;j<4;j++)
        {
            NCasilla aux = this.matriz.Insertar(i, j, 1,new Vacio());
            NM auxx = aux.Buscar(1);
            auxx.setTransferHandler(new ImageSelection(this.lista,this));
            auxx.addMouseListener(this);
            auxx.setBounds(j*75, i*75, 75, 75);
            this.add(auxx);
        }
    }
    
    this.repaint();
    this.scroll.setBounds(20,100,this.getWidth()+400,this.getHeight()+300);
    refreshActual();
    this.General.add(this.scroll);
    
    
 }
 
 public void AgregarFila()
 {
     System.out.println("FILAS ANSTE de agregar filas: "+this.matriz.getLcolumnas().ListaFilas.elementos);
     System.out.println("COLUMNAS ANTES de agregar filas: "+this.matriz.getLcolumnas().elementos);
     int filnueva = this.matriz.getLcolumnas().ListaFilas.elementos;
     for(int i=0;i<this.matriz.getLcolumnas().elementos;i++)
     {
         
         
         
         NCasilla aux = this.matriz.Insertar(filnueva,i,1,new Vacio());
         NM auxx = aux.Buscar(1);
         auxx.setTransferHandler(new ImageSelection(this.lista,this));
         auxx.addMouseListener(this);
        
         
         
     }
     System.out.println("FILAS DESPUES de agregar filas: "+this.matriz.getLcolumnas().ListaFilas.elementos);
     System.out.println("COLUMNAS DESPUES de agregar filas: "+this.matriz.getLcolumnas().elementos);
     MakeTablero();
 }
 public void AgregarColumna()
 {
    int colnueva = this.matriz.getLcolumnas().elementos;
    for(int i=0;i<this.matriz.getLcolumnas().ListaFilas.elementos;i++)
    {
        
        
        NCasilla aux = this.matriz.Insertar(i,colnueva,1,new Vacio());
        NM auxx = aux.Buscar(1);
        auxx.setTransferHandler(new ImageSelection(this.lista,this));
        auxx.addMouseListener(this);
    }
    MakeTablero(); 
 }
 public void MakeTablero()
 {
    this.removeAll();
    System.out.println("EN EL MakeTablero");
    this.setPreferredSize(new Dimension(this.matriz.getLcolumnas().elementos*75,this.matriz.getLcolumnas().ListaFilas.elementos*75));
    scroll.setViewportView(this);
    int x = 0;
    
    NC columna = this.matriz.getLcolumnas().Primero;
    while(columna!=null)
    {
        int y=0;
        NF fila = this.matriz.getLcolumnas().ListaFilas.Primero;
        while(fila!=null)
        {
            NCasilla casilla = fila.Buscar(columna);
            NM nodo = casilla.Buscar(1);
            nodo.setBounds(x*75, y*75,75,75);
            this.add(nodo);
            y++;
            fila = fila.Next;
        }
        x++;
        columna = columna.Next;
    }
    this.repaint();
    
    //this.setBounds(100,100,this.getWidth(),this.getHeight());
 }
 void refreshActual()
 {
     Objeto aux = this.lista.Siguiente();
     if(aux!=null)
     {
         Icon icon = new ImageIcon(aux.getImage());
         this.lblobjeto.setIcon(icon);
     }
     else
     {
         this.lblobjeto.setIcon(null);
     }
 }
 private void ElimFoC()
 {
     if(this.radiocolumna.isSelected())
     {
         // se quiere borrar una columna
         int col = Integer.parseInt(this.txtnum.getText());
         this.matriz.Eliminar(col,1);
     }
     else if(this.radiofila.isSelected())
     {
         int fil = Integer.parseInt(this.txtnum.getText());
         this.matriz.Eliminar(fil,0);
     }
 }
         
 protected void paintComponent(Graphics g)
 {
     
     super.paintComponent(g);
     Dimension d = getSize();
     //setOpaque(false);
     g.drawImage(imgs.fondomaker(),0,0,this.matriz.getLcolumnas().elementos*75,this.matriz.getLcolumnas().ListaFilas.elementos*75,this);
 }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==this.btnplay)
        {
            int x = 0;
            
            NC columna = this.matriz.getLcolumnas().Primero;
            while(columna!=null)
            {
                NF fila =  this.matriz.getLcolumnas().ListaFilas.Primero;
                int y=0;
                while(fila!=null)
                {
                    NCasilla cas = columna.Buscar(fila);
                    NM casilla = cas.Buscar(1);
                    if(casilla.Dato!=null)
                    {
                        casilla.Dato.setCordx(x);
                        casilla.Dato.setCordy(y);
                        casilla.Dato.setPosfx(x);
                        casilla.Dato.setPosfy(y);
                        casilla.Dato.casillaactual = cas;
                    }
                    else if(casilla.Dato==null)
                    {
                        casilla.Dato = new Vacio();
                        casilla.Dato.setCordx(x);
                        casilla.Dato.setCordy(y);
                        casilla.Dato.setPosfx(x);
                        casilla.Dato.setPosfy(y);
                        casilla.Dato.casillaactual = cas;
                        
                    }
                    
                    y=y+75;
                    fila = fila.Next;
                }
                
                columna = columna.Next;
                x = x+75;
            }
            //se le da las posiciones iniciales a los objetos!
            
            // agregar a la matriz dos filas arriba(Al inicio)
            int columnas = this.matriz.getLcolumnas().elementos;
            for(int i=-1;i>-3;i--)
            {
                System.out.println("EN EL FOR1");
                for(int j=0;j<columnas;j++)
                {
                    System.out.println("EN EL FOR2");
                    this.matriz.Insertar(i, j,1,new Vacio());
                }
            }
            
            //agregar a la matriz una fila al final
            int fila = this.matriz.getLcolumnas().ListaFilas.elementos;
            for(int i=0;i<columnas;i++)
            {
                System.out.println("EN EL FOR3");
                this.matriz.Insertar(fila, i, 1,new Vacio());
            }
            game = new Game(this.matriz);
            
        }
            
        else if(e.getSource()==this.btnaddC)
        {
            this.AgregarColumna();
            
        }
        else if(e.getSource()==this.btnaddF)
        {
            //aggregar fila
            this.AgregarFila();
            
        }
        else if(e.getSource()==this.btnelim)
        {
            // se elimina la fila o columna seleccionada
            if(!this.txtnum.getText().equals(""))
                this.ElimFoC();
        }
        else if(e.getSource()==this.btngraflista)
        {
            // se grafica la lista
            this.lista.Graficar("Lista");
        }
        else if(e.getSource()==this.btngrafmatrziz)
        {
            // graficar matri
            this.matriz.graficarMDispersa();
        }
    }

   

    @Override
    public void stateChanged(ChangeEvent e) {
        /*
        if(e.getSource()==this.radiocolumna)
        {
            this.matriz.Eliminar(Integer.parseInt(this.txtnum.getText()),1);
        }
        else if(e.getSource()==this.radiofila)
        {
            this.matriz.Eliminar(Integer.parseInt(this.txtnum.getText()),0);
        }
                */
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("********************MOUSE CLICKED****************");
        System.out.println("NUMERO DE ELEMENTOS EN LA LISTA ANTES DE QUITAR DEL PANEL Y AGREGARLA A LA LISTA: "+this.lista.getElementos());
        NM panel = (NM)e.getSource();
        this.lista.Add(panel.Dato);
        panel.Dato=new Vacio();
        System.out.println("NUMERO DE ELEMENTOS EN LA LISTA DESPUES DE QUITAR DEL PANEL Y AGREGARLA A LA LISTA: "+this.lista.getElementos());
        System.out.println("ID DEL OBJETO EN EL PANEL: "+panel.Dato.getId());
        this.refreshActual();
        
        panel.repaint();
        this.areatxt.setText(this.lista.Restantes().toString());
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //*throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 
 
 
}
