/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Imagenes.imagen;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author byron
 */
public abstract class Objeto extends ImageIcon {
    private int cordx,cordy,vx,vy,posfx,posfy;
    private imagen imgs;
    public int id;
    public Objeto()
    {
        imgs = new imagen();
        
    }
    public abstract void render(Graphics g);
            
    
    public abstract void tick();

    /**
     * @return the cordx
     */
    public int getCordx() {
        return cordx;
    }

    /**
     * @param cordx the cordx to set
     */
    public void setCordx(int cordx) {
        this.cordx = cordx;
    }

    /**
     * @return the cordy
     */
    public int getCordy() {
        return cordy;
    }

    /**
     * @param cordy the cordy to set
     */
    public void setCordy(int cordy) {
        this.cordy = cordy;
    }

    /**
     * @return the vx
     */
    public int getVx() {
        return vx;
    }

    /**
     * @param vx the vx to set
     */
    public void setVx(int vx) {
        this.vx = vx;
    }

    /**
     * @return the vy
     */
    public int getVy() {
        return vy;
    }

    /**
     * @param vy the vy to set
     */
    public void setVy(int vy) {
        this.vy = vy;
    }

    /**
     * @return the posfx
     */
    public int getPosfx() {
        return posfx;
    }

    /**
     * @param posfx the posfx to set
     */
    public void setPosfx(int posfx) {
        this.posfx = posfx;
    }

    /**
     * @return the posfy
     */
    public int getPosfy() {
        return posfy;
    }

    /**
     * @param posfy the posfy to set
     */
    public void setPosfy(int posfy) {
        this.posfy = posfy;
    }

    /**
     * @return the imgs
     */
    public imagen getImgs() {
        return imgs;
    }

    /**
     * @param imgs the imgs to set
     */
    public void setImgs(imagen imgs) {
        this.imgs = imgs;
    }
    
    
    
}
