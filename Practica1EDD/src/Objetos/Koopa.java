/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Imagenes.imagen;
import MDisp.NCasilla;
import MDisp.NM;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author byron
 */
public class Koopa extends Objeto {
    
    private boolean caminando,cayendo,topandox,topandoy,derecha;
    imagen imagenes;
    int constante = 1,pixelesx=0,pixelesy=0;
    
    public Koopa(String nom,Image img)
    {
        lienzo = null;
        caminando = true;
        cayendo = false;
        topandox = false;
        topandoy=false;
        this.setNombre(nom);
        this.setId(3);
        this.setCordx(0);
        this.setCordy(0); 
        this.setImage(img);
        this.setPosfx(0);
        this.setPosfy(0);
        this.setVx(5);
        this.setVy(0);
        this.viviente = true;
        imagenes = new imagen();
        casillaactual = null;
    }
    
    

    

    

    @Override
    public void render(Graphics g) {
         if(this.lienzo==null)
             System.out.println("EL LIENZO ES NULO");
         else
             System.out.println("EL LIENZO NO ES NULO");
         if(g==null)
             System.out.println("EL g ES NULO");
         else
             System.out.println("EL g NO ES NULO");
             
        this.lienzo.getGraphics().drawImage(imagenes.derqk1(),this.getCordx(),this.getCordy(),75,75, this.lienzo);
        
    }

    @Override 
    public void tick() {
        NM nm = casillaactual.Buscar(1); 
        if(nm.Dato.getCordx()==nm.Dato.getPosfx()&&nm.Dato.getCordy()==nm.Dato.getPosfy())
        {
            if(caminando)
            {
                if(this.getVx()>0)
                {
                    //caminando hacia la derecha
                   NM nmder = casillaactual.Derecha.Buscar(1);
                   nmder.Dato  = nm.Dato;
                   nm.Dato = null;
                   this.casillaactual = this.casillaactual.Derecha;
                    
                }
                else if(this.getVx()<0)
                {
                    //caminando hacia la izquirda
                   NM nmizq = casillaactual.Izquierda.Buscar(1);
                   nmizq.Dato  = nm.Dato;
                   nm.Dato = null;
                   this.casillaactual = this.casillaactual.Izquierda;
                }
            }
            else if(cayendo)
            {
                   NM nmabajo = casillaactual.Abajo.Buscar(1);
                   nmabajo.Dato  = nm.Dato;
                   nm.Dato = null;
                   this.casillaactual = this.casillaactual.Abajo;
            }
            //todavia no a caminado nada desde su pos en la matriz actual.
            if(casillaactual.Abajo.Buscar(1).Dato.getId()==-1)
            {
                //entonces si hay en donde sostenerse para caminar.
                
                nm.Dato.setPosfx(nm.Dato.getCordx()+75);
                caminando = true; 
            }
            else
            {
                
            }
        }
        
        else if(caminando)
        {
            if(topandox)
            {
                if(this.getVx()>0)
                {
                    // esta avanzando a la derecha
                    //*cambiar a imagen izquierda
                    derecha = false;
                    this.setImage(imagenes.generalkoopa(derecha,constante));
                    
                    this.setVx(-5);
                    this.setVy(0);
                    
                }
                else
                {
                    // esta avanzando a la izquierda
                    derecha = true;
                    this.setImage(imagenes.generalkoopa(derecha, constante));
                    
                    this.setVx(5);
                    this.setVy(0);
                }
            }
            else
            {
                this.setVx(5);
                this.setVy(0);
                if(constante==1)
                {
                    constante++;
                    this.setImage(imagenes.generalkoopa(derecha, constante));
                }
                else if(constante==2)
                {
                    constante--;
                    this.setImage(imagenes.generalkoopa(derecha, constante));
                    
                }
            }
            //this.setVx(5);
            
            
        }
        
        else if(cayendo)
        {
            this.setVx(0);
            this.setVy(+5);
        }
        
        
    }
    
    
}
