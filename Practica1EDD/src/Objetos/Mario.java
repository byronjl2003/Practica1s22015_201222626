/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Imagenes.imagen;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author byron
 */
public class Mario extends Objeto {
    
    

    

    @Override
    public void setImage(Image image) {
        super.setImage(this.getImgs().mario1()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
