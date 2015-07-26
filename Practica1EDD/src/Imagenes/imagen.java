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
    
    
}
