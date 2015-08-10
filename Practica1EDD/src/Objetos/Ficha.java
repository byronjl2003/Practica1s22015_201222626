/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import GUI.Game;
import Imagenes.imagen;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author byron
 */
public class Ficha extends Objeto {
    imagen imgs;
    public Ficha(String nom,Image img)
    {
        this.imgs = new imagen();
        this.setNombre(nom);
        this.setId(4);
        this.viviente = false;
        this.setCordx(0);
        this.setCordy(0); 
        this.setImage(img);
        this.setPosfx(0);
        this.setPosfy(0);
        this.setVx(5);
        this.setVy(0);
    }
    
    

    

    

    @Override
    public void render(Graphics g,Game game) {
        g.drawImage(this.imgs.generalficha(),this.getCordx(),this.getCordy(),75,75,game);
    }

    @Override
    public void tick() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void die() {
       this.lista.EliminarDeLista(this.nodol);
    }
    
    
}
