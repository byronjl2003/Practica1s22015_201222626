/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Imagenes.imagen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author byron
 */
public class VentanaPrincipal extends JPanel implements ActionListener {
    
    JButton btncargar,btnmaker;
    JFrame vent;
    imagen imgs;
    JPanel jpcarga,jpmaker;
    //-------elementos para el panel de carga
    JPanel jpsuelo,jppared,jpgoomba,jpkoopa,jpficha,jphongo,jppersonaje,jpcastillo;
    JButton btnsuelo,btnpared,btngoomba,btnkoopa,btnficha,btnhongo,btnpersonaje,btncastillo;
    JTextField lblsuelo,lblpared,lblgoomba,lblkoopa,lblficha,lblhongo,lblpersonaje,lblcastillo;
    //elementos para el panel maker
    
    public VentanaPrincipal()
    {
        System.out.println("ddsdsdsd");
        imgs = new imagen();
        vent= new JFrame("MARIO MAKER!");
        vent.setSize(1100,700);
        vent.getContentPane().setLayout(null);
        vent.setResizable(false);
        //vent.setLocationRelativeTo(null);
        vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1100,700);
        this.setLayout(null);
        vent.add(this);
        
        btncargar = new JButton();
        btncargar.addActionListener(this);
        btncargar.setBounds(50,150,90,50);
        this.add(btncargar);
        btnmaker = new JButton();
        btnmaker.addActionListener(this);
        btnmaker.setBounds(150, 150, 90, 50);
        this.add(btnmaker);
        vent.setVisible(true);
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
            this.jpmaker.setVisible(false);
        }
        else if(e.getSource()==this.btnmaker)
        {
            this.jpcarga.setVisible(false);
            this.jpmaker.setVisible(true);
        }
    }
   
    
}
