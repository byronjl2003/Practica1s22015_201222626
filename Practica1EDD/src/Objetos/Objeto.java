/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import GUI.Game;
import Imagenes.imagen;
import MDisp.NCasilla;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author byron
 */
public abstract class Objeto extends ImageIcon {
    private int cordx,cordy,with,height,vx,vy,posfx,posfy;
    public boolean viviente;
    private int id;
    private String nombre;
    public NCasilla casillaactual;
   
    public Objeto()
    {
        casillaactual = null;
        
    }
    public abstract void render(Graphics g,Game game);
            
    
    public abstract void tick();
    public abstract void die();
    
            
    

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
     * @return the with
     */
    public int getWith() {
        return with;
    }

    /**
     * @param with the with to set
     */
    public void setWith(int with) {
        this.with = with;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    
}
