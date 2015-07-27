/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author byron
 */
public class Maker extends JPanel {
 JScrollPane scroll;
 public Maker()
 {
    scroll = new JScrollPane();
    this.setLayout(new GridLayout());
    this.setPreferredSize(new Dimension(250,500));
    scroll.setViewportView(this);
 }
 protected void paintComponent(Graphics g)
 {
     
 }
    
 
 
 
}
