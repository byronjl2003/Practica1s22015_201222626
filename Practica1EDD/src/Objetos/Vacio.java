/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import GUI.Game;
import java.awt.Graphics;

/**
 *
 * @author byron
 */
public class Vacio extends Objeto {
    
    public Vacio()
    {
        
        this.setNombre("VACIO");
        this.setId(-1);
        this.setCordx(0);
        this.setCordy(0); 
        //this.setImage(null);
        this.setPosfx(0);
        this.setPosfy(0);
        this.setVx(0);
        this.setVy(0);
        this.viviente = false;
        
        casillaactual = null;
    }

    @Override
    public void render(Graphics g,Game game) {
       
    }

    @Override
    public void tick() {
       
    }

    @Override
    public void die() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
