/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import GUI.Game;
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
public class Koopa extends Objeto   {
    
    private boolean derecha;
    imagen imagenes;
    int constante = 1,pixelesx=0,pixelesy=0,cuadros = 0;
    
    public Koopa(String nom,Image img)
    {
        
        
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
        this.derecha = true;
    }
    
    

    

    

    @Override
    public void render(Graphics g,Game game) {
         
        //System.out.println("EN EL REnder");
        
        if(constante==2)
            constante--;
        else
            constante++;
                
        this.setCordx(this.getCordx() + this.getVx());
        this.setCordy(this.getCordy() + this.getVy());
        g.drawImage(this.imagenes.generalkoopa(derecha,constante),this.getCordx(),this.getCordy(),75,75,game);
        
    }

    @Override 
   public void tick()
    {
        if(this.getPosfx()==this.getCordx() && this.getPosfy()==this.getCordy())
        {
           // System.out.println("ENTRO");
            if(this.casillaactual.Abajo==null)
            {
                // actualmente koopa esta en la fila en donde debe morir
                this.die();
            }
            else
            if(this.casillaactual.Abajo.Buscar(1).Dato.getId()==-1)
            {
                // La tortuga no tiene piso abajo
                //se cambia casillaactual.
               // System.out.println("LA TORTUGA NO TIENE PISO");
                Objeto aux = this.casillaactual.Abajo.Buscar(1).Dato;
                this.casillaactual.Abajo.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                this.casillaactual.Buscar(1).Dato = aux;
                
                this.casillaactual = this.casillaactual.Abajo;
                this.setPosfy(this.getCordy()+75);
                this.setVy(+5);
                this.setVx(0);
            }
            else if(this.casillaactual.Abajo.Buscar(1).Dato.getId()==0 |this.casillaactual.Abajo.Buscar(1).Dato.getId()==1 )
            {
               // System.out.println("LA TORTUGA SI TIENE PISO");
                if(this.casillaactual.Derecha==null)
                {
                   // System.out.println("LA TORTUGA ESTA EN EL BORDE DERECHO");
                    derecha = false;// para cambiar la imagen de derecha a izquierda
                    Objeto aux = this.casillaactual.Izquierda.Buscar(1).Dato;
                    this.casillaactual.Izquierda.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                    this.casillaactual.Buscar(1).Dato = aux;
                    
                    this.casillaactual = this.casillaactual.Izquierda;
                    this.setPosfx(this.getCordx()-75);
                    this.setVx(-5);
                    this.setVy(0);
                    //la tortuga esta en tope derecho
                    
                }
                else if(this.casillaactual.Izquierda==null)
                {
                    // la tortuga esta en el tope izquierdo
                    //System.out.println("LA TORTUGA ESTA EN EL BORDE IZQUIERDO");
                    derecha = true;//para cambiar la imagen de izquierda a derecha
                    
                    Objeto aux = this.casillaactual.Derecha.Buscar(1).Dato;
                    this.casillaactual.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                    this.casillaactual.Buscar(1).Dato = aux;
                    
                    this.casillaactual = this.casillaactual.Derecha;
                    this.setPosfx(this.getCordx()+75);
                    this.setVx(5);
                    this.setVy(0);
                    
                }
                else
                {
                    //se verifica si hay topes
                    if((this.casillaactual.Derecha.Buscar(1).Dato.getId()==0|this.casillaactual.Derecha.Buscar(1).Dato.getId()==1)&&(this.casillaactual.Izquierda.Buscar(1).Dato.getId()==0|this.casillaactual.Izquierda.Buscar(1).Dato.getId()==1))
                    {
                       // System.out.println("ESTA ATRAPADO!!!!");
                        this.setVx(0);
                        this.setVy(0);
                        // esta atraoado!!!!!
                    }
                    else if(derecha)
                    {
                        if(this.casillaactual.Derecha.Buscar(1).Dato.getId()==0|this.casillaactual.Derecha.Buscar(1).Dato.getId()==1)
                        {
                            //hay tope, se cambia de giro la imagen y la casilla a la izquierda.
                          //  System.out.println("ESTA DETECTANDO EL TOPE A LA DERECHA");
                            derecha = false;
                            Objeto aux = this.casillaactual.Izquierda.Buscar(1).Dato;
                            this.casillaactual.Izquierda.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            this.casillaactual = this.casillaactual.Izquierda;
                            this.setPosfx(this.getCordx()-75);
                            this.setVx(-5);
                            this.setVy(0);
                        }
                        else if(this.casillaactual.Derecha.Buscar(1).Dato.getId()==6)
                        {
                           // System.out.println("es un mario!, iendo hacia la derecha");
                            Mario mario = (Mario)this.casillaactual.Derecha.Buscar(1).Dato;
                            mario.die();
                            if(this.casillaactual.Derecha.Buscar(1).Dato.getId()==6)
                            {
                                // despues de morir mario sigue con vida
                                if(this.casillaactual.Derecha.Derecha!=null)
                                {
                                    if(this.casillaactual.Derecha.Derecha.Buscar(1).Dato.getId()==-1)
                                    {
                                        // esta vacia la casilla derecha de mario
                                        derecha = true;
                                        Objeto aux = this.casillaactual.Derecha.Derecha.Buscar(1).Dato;
                                        this.casillaactual.Derecha.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                                        this.casillaactual.Buscar(1).Dato = aux;
                            
                                        this.casillaactual = this.casillaactual.Derecha.Derecha;
                                        this.setPosfx(this.getCordx()+150);
                                        this.setVx(10);
                                        this.setVy(0);
                                   
                                        
                                    }
                                    else
                                    {
                                        derecha = false;
                                        Objeto aux = this.casillaactual.Izquierda.Buscar(1).Dato;
                                        this.casillaactual.Izquierda.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                                        this.casillaactual.Buscar(1).Dato = aux;
                            
                                        this.casillaactual = this.casillaactual.Izquierda;
                                        this.setPosfx(this.getCordx()-75);
                                        this.setVx(-5);
                                        this.setVy(0);
                                    }
                                }
                               
                               else
                               {
                                   derecha = false;
                                    Objeto aux = this.casillaactual.Izquierda.Buscar(1).Dato;
                                    this.casillaactual.Izquierda.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                                    this.casillaactual.Buscar(1).Dato = aux;
                            
                                    this.casillaactual = this.casillaactual.Izquierda;
                                    this.setPosfx(this.getCordx()-75);
                                    this.setVx(-5);
                                    this.setVy(0);
                               }
                            }
                            else
                            {
                                derecha = true;
                                Objeto aux = this.casillaactual.Derecha.Buscar(1).Dato;
                            
                                this.casillaactual.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                                this.casillaactual.Buscar(1).Dato = aux;
                            
                                this.casillaactual = this.casillaactual.Derecha;
                                this.setPosfx(this.getCordx()+75);
                                this.setVx(5);
                                this.setVy(0);
                            }
                            
                            
                        }
                        else
                        {
                           // System.out.println("EN EL ELSE DE DERECHA");
                            
                            derecha = true;
                            Objeto aux = this.casillaactual.Derecha.Buscar(1).Dato;
                            
                            this.casillaactual.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            
                            this.casillaactual = this.casillaactual.Derecha;
                            this.setPosfx(this.getCordx()+75);
                            this.setVx(5);
                            this.setVy(0);
                        }
                    }
                    else
                    {
                        //izq
                        if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()==0|this.casillaactual.Izquierda.Buscar(1).Dato.getId()==1)
                        {
                            //hay tope, se cambia de giro la imagen y la casilla a la izquierda.
                           //  System.out.println("ESTA DETECTANDO EL TOPE A LA IZQUIERDA");
                            derecha = true;
                             Objeto aux = this.casillaactual.Derecha.Buscar(1).Dato;
                            this.casillaactual.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            
                            this.casillaactual = this.casillaactual.Derecha;
                            this.setPosfx(this.getCordx()+75);
                            this.setVx(5);
                            this.setVy(0);
                        }
                        else if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()==6)
                        {
                            //es un mario!
                            Mario mario = (Mario)this.casillaactual.Izquierda.Buscar(1).Dato;
                            mario.die();
                            if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()==6)
                            {
                                // despues de morir mario sigue con vida
                                if(this.casillaactual.Izquierda.Izquierda!=null)
                                {
                                    if(this.casillaactual.Izquierda.Izquierda.Buscar(1).Dato.getId()==-1)
                                    {
                                        // esta vacia la casilla izquierda de mario
                                        derecha = false;
                                        Objeto aux = this.casillaactual.Izquierda.Izquierda.Buscar(1).Dato;
                                        this.casillaactual.Izquierda.Izquierda.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                                        this.casillaactual.Buscar(1).Dato = aux;
                            
                                        this.casillaactual = this.casillaactual.Izquierda.Izquierda;
                                        this.setPosfx(this.getCordx()-150);
                                        this.setVx(-10);
                                        this.setVy(0);
                                   
                                        
                                    }
                                    else
                                    {
                                        derecha = true;
                                        Objeto aux = this.casillaactual.Derecha.Buscar(1).Dato;
                                        this.casillaactual.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                                        this.casillaactual.Buscar(1).Dato = aux;
                            
                                        this.casillaactual = this.casillaactual.Derecha;
                                        this.setPosfx(this.getCordx()+75);
                                        this.setVx(+5);
                                        this.setVy(0);
                                    }
                                }
                               
                               else
                               {
                                   derecha = true;
                                    Objeto aux = this.casillaactual.Derecha.Buscar(1).Dato;
                                    this.casillaactual.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                                    this.casillaactual.Buscar(1).Dato = aux;
                            
                                    this.casillaactual = this.casillaactual.Derecha;
                                    this.setPosfx(this.getCordx()+75);
                                    this.setVx(5);
                                    this.setVy(0);
                               }
                            }
                            else
                            {
                                derecha = true;
                                Objeto aux = this.casillaactual.Derecha.Buscar(1).Dato;
                            
                                this.casillaactual.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                                this.casillaactual.Buscar(1).Dato = aux;
                            
                                this.casillaactual = this.casillaactual.Derecha;
                                this.setPosfx(this.getCordx()+75);
                                this.setVx(5);
                                this.setVy(0);
                            }
                            
                            
                        }
                        //****************************
                        else
                        {
                            derecha = false;
                            Objeto aux = this.casillaactual.Izquierda.Buscar(1).Dato;
                            this.casillaactual.Izquierda.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            
                            this.casillaactual = this.casillaactual.Izquierda;
                            this.setPosfx(this.getCordx()-75);
                            this.setVx(-5);
                            this.setVy(0);
                        }
                    }
                }
            }
        }
        
    }

    @Override
    public void die() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
