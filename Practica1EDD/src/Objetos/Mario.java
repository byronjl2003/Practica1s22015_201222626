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
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author byron
 */
public class Mario extends Objeto {
    imagen imgs;
    int contador=0;
    int accion = -1;
    int cuadros = 0;
    LinkedList cola;
    char presionado ;
    long lastKeyPress = System.currentTimeMillis();
    public boolean bloqueo,fatal,derecha;
    public int puntos,vidas;
    Game game;
    public Mario(String nom,Image img)
    {
        this.vidas = 1;
        this.puntos = 0;
        imgs = new imagen();
        bloqueo = false;
        fatal = false;
        cola = new LinkedList();
        this.setNombre(nom);
        this.setId(6);
        this.setCordx(0);
        this.setCordy(0); 
        if(img!=null)
        this.setImage(img);
        this.setPosfx(0);
        this.setPosfy(0);
        this.setVx(5);
        this.setVy(0);
        this.viviente = true;
    }
    
    
    
    
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //System.out.println("presionando "+e.getKeyChar());
        //1 izquierda,2 arriba,3 derecha,4 izq-arriba,5 der-arriba,0 bajarf
       // System.out.println("EN EL KEY DE MARIO");
        if(!bloqueo)
        {
         //   System.out.println("ENTRO AL IF DEL KEy");
            if(e.getKeyChar()=='a')
            {
                if(System.currentTimeMillis()-lastKeyPress<1000)
                {
                    if(presionado=='w')
                    {
                         cola.offer(4);
           //             System.out.println("salto izquierda");
                        presionado = 'p';
                    }
                    
                
                }
            
                else
                {
                    cola.offer(1);
             //       System.out.println("izquierda");
                }
                
                
            
            
            }
            else if(e.getKeyChar()=='d')
            {
                if(System.currentTimeMillis()-lastKeyPress<1000)
                {
                    if(presionado=='w')
                    {
                        cola.offer(5);
               //         System.out.println("salto derecha");
                        presionado = 'p';  
                    }
                    
                }
            
                else
                {
                    cola.offer(3);
                 //   System.out.println("derecha");
                }
                
            
            }
            else if(e.getKeyChar()=='w')
            {
                if(presionado == 'w')
                {
                    cola.offer(2);
                   // System.out.println("SALTO");
                    presionado = 'p';
                }
                
                
                else
                {
                    lastKeyPress = System.currentTimeMillis();
                    presionado = e.getKeyChar();
                }
                
                
            }
        }
        else
        {
          //  System.out.println("EL OIDO ESTA BLOQUEADO!");
        }
        
        
            
        
    }
    

    

    

    @Override
    public void render(Graphics g,Game game) {
  
        if(accion!=-1)
        {
            if(contador==4)
            contador=1;
        else
            contador++;
        }
        
                
                    
        this.setCordx(this.getCordx() + this.getVx());
        this.setCordy(this.getCordy() + this.getVy());
        g.drawImage(imgs.generalmario(derecha,contador),this.getCordx(),this.getCordy(),75,75,game);
    }
    
    private void Traeraccion()
    {
        
        if(cola.peek()!=null)
            accion= (int) cola.poll();
        else
            accion = -1;
        //System.out.println("EN TRAER ACCION: "+accion);
        
        
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
    }

    @Override
    public void tick() {
        
        
        if(this.getPosfx()==this.getCordx() && this.getPosfy()==this.getCordy())
        {// Cuando ya termino de recorrer un cuadro.
            //System.out.println("ENTRO");
            if(this.casillaactual.Abajo==null)
            {
                    // esta en el ultimo cuadro, se muere
                    System.out.println("SE MUERIO MARIO");
                    this.fatal = true;
                    this.die();
                    this.fatal = false;
                
            }
            else if(this.casillaactual.Abajo.Buscar(1).Dato.getId()==3&&cuadros==0)
            {
               // System.out.println("EN EL IF DE APLASTADO DE GOOMBA");
                Objeto aux =  this.casillaactual.Abajo.Buscar(1).Dato;// se guarda el goomba
                this.casillaactual.Abajo.Buscar(1).Dato = casillaactual.Buscar(1).Dato;// se pasa el mario al cuadro de abajo
                aux.casillaactual.Buscar(1).Dato = new Vacio();
                this.casillaactual = this.casillaactual.Abajo;
                aux.die();
                
                this.setPosfy(this.getCordy()+75);
                this.setVy(5);
                this.setVx(0);
            }
            else if((this.casillaactual.Abajo.Buscar(1).Dato.getId()==4||this.casillaactual.Abajo.Buscar(1).Dato.getId()==5)&&cuadros==0)
            {
                System.out.println("ENTRO A QUE SE VA A COMER algo CAYENDO");
                if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()==4)
                    this.puntos++;
                else
                    this.vidas++;
                Objeto aux = this.casillaactual.Abajo.Buscar(1).Dato;
                this.casillaactual.Abajo.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                this.casillaactual.Buscar(1).Dato = new Vacio();
                this.casillaactual = this.casillaactual.Abajo;
                this.setPosfy(this.getCordy()+75);
                this.setVx(0);
                this.setVy(+5);
                aux.die();
            }
            else if((this.casillaactual.Abajo.Buscar(1).Dato.getId()==-1 |accion==0)&&cuadros==0)
            {
                
                //se va a caer sin traer ninguna accion de la pila
                this.bloqueo = true;
               // System.out.println("EL MARIO NO TIENE PISO");
                
                Objeto aux = this.casillaactual.Abajo.Buscar(1).Dato;
                this.casillaactual.Abajo.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                this.casillaactual.Buscar(1).Dato = aux;
                this.casillaactual = this.casillaactual.Abajo;
                this.setPosfy(this.getCordy()+75);
                this.setVy(5);
                this.setVx(0);
                
            }
            else
            {
                this.bloqueo=false;
                // si hay piso para ir a traer acciones a la pila y para escuchar
                if(cuadros==0)
                {
                    //System.out.println("ENTRO A CUADROS==0");
                    this.Traeraccion();
                }
                    
                if(accion==-1)
                {
                    // no hay acciones en la pila
                   // System.out.println("NO HAY ACCIONES EN LA PILA");
                    this.setVx(0);
                    this.setVy(0);
                }
                else if(accion==1|accion==3)
                {
                    // es un movimineto a la derecha o izquierda
                    if(accion==1)
                    {
                        //System.out.println("ENTRO A Accion==1");
                        //verificar tope a la izquierda
                        if(this.casillaactual.Izquierda!=null)
                        {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
                            // Mario nO en el tope izquierdo,SE PROCEDE A VER SI HAY TOPE
                            if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()==4 ||this.casillaactual.Izquierda.Buscar(1).Dato.getId()==5)
                            {
                                derecha = false;
                                System.out.println("ENTRO A QUE SE VA A COMER algo");
                                if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()==4)
                                    this.puntos++;
                                else
                                    this.vidas++;
                                Objeto aux = this.casillaactual.Izquierda.Buscar(1).Dato;
                                this.casillaactual.Izquierda.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                                this.casillaactual.Buscar(1).Dato = new Vacio();
                                this.casillaactual = this.casillaactual.Izquierda;
                                this.setPosfx(this.getCordx()-75);
                                this.setVx(-5);
                                this.setVy(0);
                                aux.die();
                            }
                            else if(this.casillaactual.Derecha.Buscar(vidas).Dato.getId()==2||this.casillaactual.Derecha.Buscar(vidas).Dato.getId()==2)
                            {
                                derecha = false;
                                this.die();
                            }
                            else if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()!=0&&this.casillaactual.Izquierda.Buscar(1).Dato.getId()!=1)
                            {
                               // System.out.println("SE MUEVE A LA IZQUIERDA");
                                //TAMPOCO HAY NINGUN TIPO DE TOPE, se procede a avanzar a la izquierda
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
                            System.out.println("SE QUIERE AVANZAR A LA IZQUIERDA PERO YA LLEGO AL BORDE IZQUIERDO");
                       
                    }
                    else if(accion==3)
                    {
                        //System.out.println("ENTRO A Accion==3");
                        // es un movimiento a la derecha
                        if(this.casillaactual.Derecha!=null)
                        {
                            
                            
                            // Mario nO en el tope izquierdo,SE PROCEDE A VER SI HAY TOPE
                            
                            if(this.casillaactual.Derecha.Buscar(1).Dato.getId()==4 ||this.casillaactual.Derecha.Buscar(1).Dato.getId()==5)
                            {
                                derecha = true;
                                System.out.println("ENTRO A QUE SE VA A COMER algo");
                                if(this.casillaactual.Derecha.Buscar(1).Dato.getId()==4)
                                    this.puntos++;
                                else
                                    this.vidas++;
                                Objeto aux = this.casillaactual.Derecha.Buscar(1).Dato;
                                this.casillaactual.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                                this.casillaactual.Buscar(1).Dato = new Vacio();
                                this.casillaactual = this.casillaactual.Derecha;
                                this.setPosfx(this.getCordx()+75);
                                this.setVx(5);
                                this.setVy(0);
                                aux.die();
                            }
                            else if(this.casillaactual.Derecha.Buscar(vidas).Dato.getId()==2||this.casillaactual.Derecha.Buscar(vidas).Dato.getId()==2)
                            {
                                derecha = true;
                                this.die();
                            }
                            else if(this.casillaactual.Derecha.Buscar(1).Dato.getId()!=0&&this.casillaactual.Derecha.Buscar(1).Dato.getId()!=1)
                            {
                                derecha = true;
                               // System.out.println("SE MUEVE A LA DERECHA");
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
                            System.out.println("SE QUIERE AVANZAR A LA DERECHA PERO YA LLEGO AL BORDE DERECHO");
                            
                    }
                }// fin del if del movimiento de derecha o izquierda
                else if(accion==2)
                {
                    System.out.println("ENTRO A ACCION2");
                    // ES UN SALTO
                    if(cuadros==0)
                    {
                        System.out.println("ENTRO A CUADROS==0");
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4 ||this.casillaactual.Arriba.Buscar(1).Dato.getId()==5)
                        {
                            System.out.println("ENTRO A QUE SE VA A COMER algo para arriba 1r cuadro");
                                if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4)
                                    this.puntos++;
                                else
                                    this.vidas++;
                                Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                                this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                                this.casillaactual.Buscar(1).Dato = new Vacio();
                                this.casillaactual = this.casillaactual.Arriba;
                                this.setPosfy(this.getCordy()-75);
                                this.setVx(0);
                                this.setVy(-5);
                                cuadros=1;//primer cuadro saltado
                                aux.die();
                        }
                        else if((this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0)&&this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1)
                        {
                            System.out.println("1 CUADRO SALTO");
                            // verifica si el cuadro de arriba no tiene algo con que topar
                            Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                            this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            
                            this.casillaactual = this.casillaactual.Arriba;
                            this.setPosfy(this.getCordy()-75);
                            this.setVx(0);
                            this.setVy(-5);
                            cuadros=1;//primer cuadro saltado
                            
                        }
                        else
                        {
                            accion=-1;
                            this.setVx(0);
                            this.setVy(0);
                            
                        }
                       // cuadros=0;
                        
                    }
                    else if(cuadros==1)
                    {
                        System.out.println("ENTRO A CUADROS==1");
                        
                        cuadros=0;
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4 ||this.casillaactual.Arriba.Buscar(1).Dato.getId()==5)
                        {
                            System.out.println("ENTRO A QUE SE VA A COMER algo para arriba 2r cuadro");
                                if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4)
                                    this.puntos++;
                                else
                                    this.vidas++;
                                Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                                this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                                this.casillaactual.Buscar(1).Dato = new Vacio();
                                this.casillaactual = this.casillaactual.Arriba;
                                this.setPosfy(this.getCordy()-75);
                                this.setVx(0);
                                this.setVy(-5);
                                aux.die();
                        }
                        else if((this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0)&&(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1))
                        {
                            System.out.println("2 CUADRO SALTO");
                            Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                            this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            
                            this.casillaactual = this.casillaactual.Arriba;
                            this.setPosfy(this.getCordy()-75);
                            this.setVx(0);
                            this.setVy(-5);
                            
                        }
                        else
                        {
                            
                            accion=-1;
                            this.setVx(0);
                            this.setVy(0);
                        }
                        
                            
                        
                    }
                    else
                    {
                        System.out.println("HAY UN PROBLEMA CON LOS CUADROS DE SALTO");
                    }
                   
                }
                else if(accion==4)
                {
                    System.out.println("ENTRO A Accion==4");
                    // ES UN SALTO A LA IZQUIERDA
                    if(cuadros==0)
                    {
                        System.out.println("1 CUADRO SALTO");
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4 ||this.casillaactual.Arriba.Buscar(1).Dato.getId()==5)
                        {
                            System.out.println("ENTRO A QUE SE VA A COMER algo para arriba 2r cuadro");
                                if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4)
                                    this.puntos++;
                                else
                                    this.vidas++;
                                Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                                this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                                this.casillaactual.Buscar(1).Dato = new Vacio();
                                this.casillaactual = this.casillaactual.Arriba;
                                this.setPosfy(this.getCordy()-75);
                                this.setVx(0);
                                this.setVy(-5);
                                cuadros=2;//primer cuadro saltado
                                aux.die();
                        }
                        else if((this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0)&&(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1))
                        {
                            // verifica si el cuadro de arriba no tiene algo con que topar
                            Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                            this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            
                            this.casillaactual = this.casillaactual.Arriba;
                            this.setPosfy(this.getCordy()-75);
                            this.setVx(0);
                            this.setVy(-5);
                            cuadros=2;//primer cuadro saltado
                            
                            
                        }
                        
                    }
                    else if(cuadros==2)
                    {
                        System.out.println("2 CUADRO SALTO");
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4 ||this.casillaactual.Arriba.Buscar(1).Dato.getId()==5)
                        {
                            System.out.println("ENTRO A QUE SE VA A COMER algo para arriba 2r cuadro");
                                if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4)
                                    this.puntos++;
                                else
                                    this.vidas++;
                                Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                                this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                                this.casillaactual.Buscar(1).Dato = new Vacio();
                                this.casillaactual = this.casillaactual.Arriba;
                                this.setPosfy(this.getCordy()-75);
                                this.setVx(0);
                                this.setVy(-5);
                                cuadros--;//primer cuadro saltado
                                aux.die();
                        }
                        else if((this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0)&&(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1))
                        {
                            Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                            this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            
                            this.casillaactual = this.casillaactual.Arriba;
                            this.setPosfy(this.getCordy()-75);
                            this.setVx(0);
                            this.setVy(-5);
                            cuadros--;
                        }
                        
                            
                        
                    }
                    else if(cuadros==1)
                    {
                        cuadros=0;
                        if(this.casillaactual.Izquierda!=null)
                        {
                            // Mario nO en el tope izquierdo,SE PROCEDE A VER SI HAY TOPE
                            if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()==4 ||this.casillaactual.Izquierda.Buscar(1).Dato.getId()==5)
                            {
                                derecha = false;
                                System.out.println("ENTRO A QUE SE VA A COMER algo");
                                if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()==4)
                                    this.puntos++;
                                else
                                    this.vidas++;
                                Objeto aux = this.casillaactual.Izquierda.Buscar(1).Dato;
                                this.casillaactual.Izquierda.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                                this.casillaactual.Buscar(1).Dato = new Vacio();
                                this.casillaactual = this.casillaactual.Izquierda;
                                this.setPosfx(this.getCordx()-75);
                                this.setVx(-5);
                                this.setVy(0);
                                aux.die();
                            }
                            else if(this.casillaactual.Derecha.Buscar(vidas).Dato.getId()==2||this.casillaactual.Derecha.Buscar(vidas).Dato.getId()==2)
                            {
                                derecha = false;
                                this.die();
                            }
                            else if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()!=0&&this.casillaactual.Izquierda.Buscar(1).Dato.getId()!=1)
                            {
                                derecha = false;
                                System.out.println("SE MUEVE A LA IZQUIERDA");
                                //TAMPOCO HAY NINGUN TIPO DE TOPE, se procede a avanzar a la izquierda
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
                         
                    else
                        cuadros--;
                    
                    
                }
                else if(accion==5)
                {
                    System.out.println("ENTRO A Accion==5");
                    // ES UN SALTO A LA DERECHA
                    if(cuadros==0)
                    {
                        System.out.println("1 CUADRO SALTO");
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4 ||this.casillaactual.Arriba.Buscar(1).Dato.getId()==5)
                        {
                            System.out.println("ENTRO A QUE SE VA A COMER algo para arriba 2r cuadro");
                                if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4)
                                    this.puntos++;
                                else
                                    this.vidas++;
                                Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                                this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                                this.casillaactual.Buscar(1).Dato = new Vacio();
                                this.casillaactual = this.casillaactual.Arriba;
                                this.setPosfy(this.getCordy()-75);
                                this.setVx(0);
                                this.setVy(-5);
                                cuadros=2;//primer cuadro saltado
                                aux.die();
                        }
                        else if((this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0)&&(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1))
                        {
                            // verifica si el cuadro de arriba no tiene algo con que topar
                            Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                            this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            
                            this.casillaactual = this.casillaactual.Arriba;
                            this.setPosfy(this.getCordy()-75);
                            this.setVx(0);
                            this.setVy(-5);
                            cuadros=2;//primer cuadro saltado
                            
                            
                        }
                        
                    }
                    else if(cuadros==2)
                    {
                        System.out.println("2 CUADRO SALTO");
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4 ||this.casillaactual.Arriba.Buscar(1).Dato.getId()==5)
                        {
                            System.out.println("ENTRO A QUE SE VA A COMER algo para arriba 2r cuadro");
                                if(this.casillaactual.Arriba.Buscar(1).Dato.getId()==4)
                                    this.puntos++;
                                else
                                    this.vidas++;
                                Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                                this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                                this.casillaactual.Buscar(1).Dato = new Vacio();
                                this.casillaactual = this.casillaactual.Arriba;
                                this.setPosfy(this.getCordy()-75);
                                this.setVx(0);
                                this.setVy(-5);
                                cuadros--;//primer cuadro saltado
                                aux.die();
                        }
                        else if((this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0)&&(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1))
                        {
                            Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                            this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            
                            this.casillaactual = this.casillaactual.Arriba;
                            this.setPosfy(this.getCordy()-75);
                            this.setVx(0);
                            this.setVy(-5);
                            cuadros--;
                        }
                        
                            
                        
                    }
                    else if(cuadros==1)
                    {
                        cuadros=0;
                        if(this.casillaactual.Derecha!=null)
                        {
                            // Mario nO en el tope izquierdo,SE PROCEDE A VER SI HAY TOPE
                            if(this.casillaactual.Derecha.Buscar(1).Dato.getId()==4 ||this.casillaactual.Derecha.Buscar(1).Dato.getId()==5)
                            {
                                derecha = true;
                                System.out.println("ENTRO A QUE SE VA A COMER algo");
                                if(this.casillaactual.Derecha.Buscar(1).Dato.getId()==4)
                                    this.puntos++;
                                else
                                    this.vidas++;
                                Objeto aux = this.casillaactual.Derecha.Buscar(1).Dato;
                                this.casillaactual.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;//se pasa a mario al cuadro de la izquierda
                                this.casillaactual.Buscar(1).Dato = new Vacio();
                                this.casillaactual = this.casillaactual.Derecha;
                                this.setPosfx(this.getCordx()+75);
                                this.setVx(5);
                                this.setVy(0);
                                aux.die();
                            }
                            else if(this.casillaactual.Derecha.Buscar(vidas).Dato.getId()==2||this.casillaactual.Derecha.Buscar(vidas).Dato.getId()==2)
                            {
                                derecha = true;
                                this.die();
                            }
                            else if(this.casillaactual.Derecha.Buscar(1).Dato.getId()!=0&&this.casillaactual.Derecha.Buscar(1).Dato.getId()!=1)
                            {
                                derecha = true;
                                System.out.println("SE MUEVE A LA DERECHA");
                                Objeto aux = this.casillaactual.Derecha.Buscar(1).Dato;
                                this.casillaactual.Derecha.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                                this.casillaactual.Buscar(1).Dato = aux;
                            
                                this.casillaactual = this.casillaactual.Derecha;
                                this.setPosfx(this.getCordx()+75);
                                this.setVx(5);
                                this.setVy(0);
                            }
                        }
                    }
                         
                    else
                        cuadros--;
                    
                    
                    
                }
                
            }
        }
        }

    @Override
    public void die() 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("EN EL DIE");
        if(fatal)
        {
            // se cayo, no importa las vidas
            this.game.play = false;
            this.game.finish = true;
            
            fatal = false;
            
        }
        else
        {
            if(vidas>1)
            {
                vidas--;
            }
            else
            {
                this.game.finish = true;
                this.game.play = false;
            }
                
        }
    }

    

    
        
        
        
    }

    
    

