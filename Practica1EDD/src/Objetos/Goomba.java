/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Imagenes.imagen;
import ListaPilaCola.Lista;
import MDisp.MDisp;
import MDisp.NC;
import MDisp.NCasilla;
import MDisp.NF;
import MDisp.NM;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author byron
 */
public class Goomba extends ObjetoViv {
    private boolean caminando,cayendo,topandox,topandoy,derecha;
    imagen imagenes;
    int constante = 1,pixelesx=0,pixelesy=0;
    NCasilla casillaactual;
    public Goomba(String nom,Image img)
    {
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            if(casillaactual.Abajo!=null)
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

    @Override
    public void gravedad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refreshmatriz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void muere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void topa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void aplastado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
