/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//hola!!!!!!
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author byron
 */
public class Cronometro extends JPanel implements Runnable  {
    
    JLabel lblseg,lblmin;
    int minutos=0,segundos=0;
    boolean start = true,finish = false;
    
    public Cronometro()
    {
        
        this.lblseg = new JLabel();
        this.lblseg.setText(segundos+"");
        this.lblmin = new JLabel();
        this.lblmin.setText(minutos+"");
        lblseg.setOpaque(false);
        lblseg.setForeground(Color.ORANGE);
        lblseg.setFont(new Font("Courier New", Font.ITALIC,20));
        lblmin.setOpaque(false);
        lblmin.setForeground(Color.ORANGE);
        lblmin.setFont(new Font("Courier New", Font.ITALIC, 20));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(1,2));
        this.add(this.lblmin);
        this.add(this.lblseg);
                
        
        
        
    }
    public void stop()
    {
        start = false;
    }
    public void play()
    {
        start = true;
    }
    public void reiniciar()
    {
        this.minutos=0;
        this.segundos = 0;
        start = true;
    }
    @Override
    public void run() {
        while(!finish)
        {
            //System.out.println("11");
            while(start)
            {
               // System.out.println("22");
                if(segundos==59)
                {
                    minutos++;
                    segundos=0;
                }
                else
                {
                    segundos++;
                }
                this.lblseg.setText(segundos+"");
                this.lblmin.setText(minutos+"");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
}
