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
public abstract class ObjetoViv extends Objeto {

    @Override
    public void render(Graphics g,Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public abstract void gravedad();
    public abstract void refreshmatriz();
    public abstract void muere();
    public abstract void topa();
    public abstract void aplastado();
    
}
