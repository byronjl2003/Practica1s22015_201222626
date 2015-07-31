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
    public JScrollPane scroll;
    Lista lista;
    int posy = 0;
    public PanelLista(Lista lista)
    {
        this.setLayout(null);
        this.lista = lista;
        scroll = new JScrollPane(this);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setPreferredSize(new Dimension(250,1500));
        
        this.setBackground(Color.MAGENTA);
        
    }
    public void llenar()
    {
       System.out.println("EN LLENAR HAY: "+lista.getElementos());
       this.removeAll();
       NL aux = this.lista.getPrimero();
       while(aux!=null)
       {
           Nodo n = new Nodo(aux,this);
           n.setBounds(0, posy,250,75);
           this.add(n);
           posy = posy+76;
           aux = aux.getNext();
                   
       }
       this.repaint();
    }
    
    
    
    private class Nodo extends JPanel implements ActionListener
    {
        JButton btnelim;
        JLabel lblnombre;
        JPanel jpimg;
        PanelLista panel;
        NL objeto;
        public Nodo(NL obj,PanelLista pa)
        {
            this.objeto = obj;
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
                    g.drawImage(obj.getObjeto().getImage(),0,0,d.width,d.height,this);
                }
 
 
            };
            lblnombre = new JLabel(obj.getObjeto().nombre);
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
                lista.EliminarDeLista(objeto);
                this.panel.posy=0;
                this.panel.llenar();
            }
        }
        
    }
    
}
