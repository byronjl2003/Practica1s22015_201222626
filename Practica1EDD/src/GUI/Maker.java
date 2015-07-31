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
import MDisp.NCasilla;
import MDisp.NM;
import Objetos.Mario;
import Objetos.Objeto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javafx.beans.value.ObservableValue;
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
public class Maker extends JPanel implements ChangeListener,ActionListener {
 JScrollPane scroll,scroll2;
 MDisp matriz;
 imagen imgs;
 JButton btnaddF,btnaddC,btnelim,btngraflista,btngrafmatrziz;
 JTextField txtnum;
 JRadioButton radiofila,radiocolumna;
 ButtonGroup bg;
 JLabel lblobjeto;
 JTextArea areatxt;
 JPanel General;
 Lista lista;
 NL actual;

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
        this.scroll2.setBounds(430,10,75,75);
        this.General.add(this.scroll2);
        this.btngraflista.setBounds(515, 15, 100, 30);
        this.General.add(this.btngraflista);
        this.btngrafmatrziz.setBounds(515, 50, 100, 30);
        this.General.add(this.btngrafmatrziz);
        this.lblobjeto.setTransferHandler(new ImageSelection(this.lista,this));
        MouseListener listener = new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
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
            NCasilla aux = this.matriz.Insertar(i, j, 1,new Mario("mario",null));
            NM auxx = aux.Buscar(1);
            auxx.setTransferHandler(new ImageSelection(this.lista,this));
            auxx.setBounds(j*75, i*75, 75, 75);
            this.add(auxx);
        }
    }
    
    this.repaint();
    this.setBounds(100,100,this.getWidth(),this.getHeight());
    refreshActual();
    this.General.add(this);
    
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
 protected void paintComponent(Graphics g)
 {
     
     super.paintComponent(g);
     Dimension d = getSize();
     //setOpaque(false);
     g.drawImage(imgs.fondomaker(),0,0,d.width,d.height,this);
 }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
 
 
 
}
