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
public class Pared extends Objeto {
    
    
    imagen img;
    public Pared(String nom,Image img)
    {
        this.img = new imagen();
        this.setNombre(nom); 
        this.setId(1);
        this.setCordx(0);
        this.setCordy(0); 
        this.setImage(img);
        this.setPosfx(0);
        this.setPosfy(0);
        this.setVx(5);
        this.setVy(0);
        this.viviente = false;
        this.lienzo = null;
    }
    
    

    

    

    @Override
    public void render(Graphics g) {
        g.drawImage(this.getImage(),this.getCordx(),this.getCordy(),75,75, this.lienzo);
    }

    @Override
    public void tick() {
        
    }
    
}
