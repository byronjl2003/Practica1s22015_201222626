/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ListaPilaCola.Lista;
import ListaPilaCola.NL;
import Objetos.Objeto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author byron
 */
public class PanelLista extends JPanel  {
    JScrollPane scroll;
    Lista lista;
    public PanelLista(Lista lista)
    {
        this.lista = lista;
        scroll = new JScrollPane();
        this.setLayout(new GridLayout(0,1,10,10));
        this.setPreferredSize(new Dimension(250,500));
        scroll.setViewportView(this);
        this.setBackground(Color.MAGENTA);
    }
    public void llenar()
    {
       this.removeAll();
       NL aux = this.lista.getPrimero();
       while(aux!=null)
       {
           this.add(new Nodo(aux.getObjeto(),this));
           aux = aux.getNext();
                   
       }
    }
    
    
    
    private class Nodo extends JPanel implements ActionListener
    {
        JButton btnelim;
        JLabel lblnombre;
        JPanel jpimg;
        PanelLista panel;
        public Nodo(Objeto obj,PanelLista pa)
        {
            this.panel = pa;
            this.setLayout(null);
            this.setSize(250,75);
            jpimg = new JPanel()
            {
                public void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    Dimension d = getSize();
                    setOpaque(false);
                    g.drawImage(obj.getImage(),0,0,d.width,d.height,this);
                }
 
 
            };
            lblnombre = new JLabel(obj.nombre);
            btnelim = new JButton("x");
            btnelim.addActionListener(this);
            jpimg.setBounds(2,2,98,70);
            this.add(jpimg);
            lblnombre.setBounds(101,15,75,30);
            this.add(lblnombre);
            btnelim.setBounds(190, 30, 30, 30);
            this.add(btnelim);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btnelim)
            {
                lista.Eliminar();
                this.panel.llenar();
            }
        }
        
    }
    
}
