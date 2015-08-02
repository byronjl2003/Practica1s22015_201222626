/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagenes;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author byron
 */
public class imagen {
    
    public imagen(){}
    public Image generalkoopa(boolean der,int num)
    {
        if(der)
        {
            if(num==1)
                return this.derqk1();
            else if(num==2)
                return this.derqk2();
                        
                
        }
        else
        {
            if(num==1)
                return this.izqk1();
            else if(num==2)
                return this.izqk2();
        }
        return null;
    }
    public Image general(int id)
    {
        switch(id)
        {
            case 0:
                return this.piso();
            case 1:
                return this.pared();
            case 2:
                return this.derg1();
            case 3:
                return this.derqk1();
            case 4:
                return this.ficha1();
            case 5:
                return this.hongo();
            case 6:
                return this.mario1();
            case 7:
                return this.castillo();
            default:
                return null;
                
                
                
              
                
                
        }
    }
    public Image fondomaker()
    {
        return new ImageIcon(getClass().getResource("FondoMario11.png")).getImage();
    }
            
    public Image mario1()
    {
        return new ImageIcon(getClass().getResource("Mario1.png")).getImage();
    }
    public Image mario2()
    {
        return new ImageIcon(getClass().getResource("Mario2.png")).getImage();
    }
    public Image mario3()
    {
        return new ImageIcon(getClass().getResource("Mario3.png")).getImage();
    }
    public Image mario4()
    {
        return new ImageIcon(getClass().getResource("Mario4.png")).getImage();
    }
    public Image piso()
    {
        return new ImageIcon(getClass().getResource("piso.png")).getImage();
    }
    public Image ficha1()
    {
        return new ImageIcon(getClass().getResource("ficha1.png")).getImage();
    }
    public Image ficha2()
    {
        return new ImageIcon(getClass().getResource("ficha2.png")).getImage();
    }
    public Image ficha3()
    {
        return new ImageIcon(getClass().getResource("ficha3.png")).getImage();
    }
    public Image ficha4()
    {
        return new ImageIcon(getClass().getResource("ficha4.png")).getImage();
    }
    public Image izqk1()
    {
        return new ImageIcon(getClass().getResource("IzqKoopa1.png")).getImage();
    }
    public Image izqk2()
    {
        return new ImageIcon(getClass().getResource("IzqKoopa2.png")).getImage();
    }
    public Image derqk1()
    {
        return new ImageIcon(getClass().getResource("DerKoopa1.png")).getImage();
    }
    public Image derqk2()
    {
        return new ImageIcon(getClass().getResource("DerKoopa2.png")).getImage();
    }
    public Image izqg1()
    {
        return new ImageIcon(getClass().getResource("IzqGoomba1.png")).getImage();
    }
    public Image izqg2()
    {
        return new ImageIcon(getClass().getResource("IzqGoomba2.png")).getImage();
    }
    public Image izqg3()
    {
        return new ImageIcon(getClass().getResource("IzqGoomba3.png")).getImage();
    }
    public Image izqg4()
    {
        return new ImageIcon(getClass().getResource("IzqGoomba4.png")).getImage();
    }
    public Image izqg5()
    {
        return new ImageIcon(getClass().getResource("IzqGoomba5.png")).getImage();
    }
    public Image derg1()
    {
        return new ImageIcon(getClass().getResource("DerGoomba1.png")).getImage();
    }
    public Image derg2()
    {
        return new ImageIcon(getClass().getResource("DerGoomba2.png")).getImage();
    }
    public Image derg3()
    {
        return new ImageIcon(getClass().getResource("DerGoomba3.png")).getImage();
    }
    public Image derg4()
    {
        return new ImageIcon(getClass().getResource("DerGoomba4.png")).getImage();
    }
    public Image derg5()
    {
        return new ImageIcon(getClass().getResource("DerGoomba5.png")).getImage();
    }
    public Image fondov()
    {
        return new ImageIcon(getClass().getResource("Fondov.jpg")).getImage();
    }
    
    public Image fondoj()
    {
        return new ImageIcon(getClass().getResource("FondoMario11.png")).getImage();
    }
    public Image pared()
    {
        return new ImageIcon(getClass().getResource("pared.png")).getImage();
    }
    public Image hongo()
    {
        return new ImageIcon(getClass().getResource("Hongo.png")).getImage();
    }
    public Image castillo()
    {
        return new ImageIcon(getClass().getResource("Hongo.png")).getImage();
    }
    
}
