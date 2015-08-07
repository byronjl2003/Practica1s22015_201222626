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
    int accion = -1;
    int cuadros = 0;
    LinkedList cola;
    char presionado ;
    long lastKeyPress = System.currentTimeMillis();
    boolean bloqueo;
    public Mario(String nom,Image img)
    {
        imgs = new imagen();
        bloqueo = false;
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
        System.out.println("EN EL KEY DE MARIO");
        if(!bloqueo)
        {
            System.out.println("ENTRO AL IF DEL KEy");
            if(e.getKeyChar()=='a')
            {
                if(System.currentTimeMillis()-lastKeyPress<1000)
                {
                    if(presionado=='w')
                    {
                         cola.offer(4);
                        System.out.println("salto izquierda");
                        presionado = 'p';
                    }
                    
                
                }
            
                else
                {
                    cola.offer(1);
                    System.out.println("izquierda");
                }
                
                
            
            
            }
            else if(e.getKeyChar()=='d')
            {
                if(System.currentTimeMillis()-lastKeyPress<1000)
                {
                    if(presionado=='w')
                    {
                        cola.offer(5);
                        System.out.println("salto derecha");
                        presionado = 'p';  
                    }
                    
                }
            
                else
                {
                    cola.offer(3);
                    System.out.println("derecha");
                }
                
            
            }
            else if(e.getKeyChar()=='w')
            {
                if(presionado == 'w')
                {
                    cola.offer(2);
                    System.out.println("SALTO");
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
            System.out.println("EL OIDO ESTA BLOQUEADO!");
        }
        
        
            
        
    }
    

    

    

    @Override
    public void render(Graphics g,Game game) {
  
        this.setCordx(this.getCordx() + this.getVx());
        this.setCordy(this.getCordy() + this.getVy());
        g.drawImage(imgs.mario1(),this.getCordx(),this.getCordy(),75,75,game);
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
            
            if((this.casillaactual.Abajo.Buscar(1).Dato.getId()==-1 |accion==0)&&cuadros==0)
            {
                
                //se va a caer sin traer ninguna accion de la pila
                this.bloqueo = true;
                System.out.println("EL MARIO NO TIENE PISO");
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
                    this.Traeraccion();
                if(accion==-1)
                {
                    // no hay acciones en la pila
                    this.setVx(0);
                    this.setVy(0);
                }
                else if(accion==1|accion==3)
                {
                    // es un movimineto a la derecha o izquierda
                    if(accion==1)
                    {
                        
                        //verificar tope a la izquierda
                        if(this.casillaactual.Izquierda!=null)
                        {
                            // Mario nO en el tope izquierdo,SE PROCEDE A VER SI HAY TOPE
                            if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()!=0&&this.casillaactual.Izquierda.Buscar(1).Dato.getId()!=1)
                            {
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
                        else
                            System.out.println("SE QUIERE AVANZAR A LA IZQUIERDA PERO YA LLEGO AL BORDE IZQUIERDO");
                       
                    }
                    else if(accion==3)
                    {
                        
                        // es un movimiento a la derecha
                        if(this.casillaactual.Derecha!=null)
                        {
                            // Mario nO en el tope izquierdo,SE PROCEDE A VER SI HAY TOPE
                            if(this.casillaactual.Derecha.Buscar(1).Dato.getId()!=0&&this.casillaactual.Derecha.Buscar(1).Dato.getId()!=1)
                            {
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
                        else
                            System.out.println("SE QUIERE AVANZAR A LA DERECHA PERO YA LLEGO AL BORDE DERECHO");
                            
                    }
                }// fin del if del movimiento de derecha o izquierda
                else if(accion==2)
                {
                    // ES UN SALTO
                    if(cuadros==0)
                    {
                        System.out.println("1 CUADRO SALTO");
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0||this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1)
                        {
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
                        
                    }
                    else if(cuadros==1)
                    {
                        System.out.println("2 CUADRO SALTO");
                        cuadros=0;
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0||this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1)
                        {
                            Objeto aux = this.casillaactual.Arriba.Buscar(1).Dato;
                            this.casillaactual.Arriba.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            
                            this.casillaactual = this.casillaactual.Arriba;
                            this.setPosfy(this.getCordy()-75);
                            this.setVx(0);
                            this.setVy(-5);
                            //cuadros--;
                        }
                        
                            
                        
                    }
                         
                    else
                        cuadros--;
                }
                else if(accion==4)
                {
                    // ES UN SALTO A LA IZQUIERDA
                    if(cuadros==0)
                    {
                        System.out.println("1 CUADRO SALTO");
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0||this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1)
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
                        
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0||this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1)
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
                            if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()!=0&&this.casillaactual.Izquierda.Buscar(1).Dato.getId()!=1)
                            {
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
                    // ES UN SALTO A LA DERECHA
                    if(cuadros==0)
                    {
                        System.out.println("1 CUADRO SALTO");
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0||this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1)
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
                        
                        if(this.casillaactual.Arriba.Buscar(1).Dato.getId()!=0||this.casillaactual.Arriba.Buscar(1).Dato.getId()!=1)
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
                            if(this.casillaactual.Derecha.Buscar(1).Dato.getId()!=0&&this.casillaactual.Derecha.Buscar(1).Dato.getId()!=1)
                            {
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
        }// fin del if de entrada
            
            
            
            
            /*
            if(this.casillaactual.Abajo==null)
            {
                // actualmente koopa esta en la fila en donde debe morir
                this.die();
            }
            else if(this.casillaactual.Abajo.Buscar(1).Dato.getId()==-1)
            {
                // La tortuga no tiene piso abajo
                //se cambia casillaactual.
                System.out.println("LA TORTUGA NO TIENE PISO");
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
                System.out.println("LA TORTUGA SI TIENE PISO");
                if(this.casillaactual.Derecha==null)
                {
                    System.out.println("LA TORTUGA ESTA EN EL BORDE DERECHO");
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
                    System.out.println("LA TORTUGA ESTA EN EL BORDE IZQUIERDO");
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
                        System.out.println("ESTA ATRAPADO!!!!");
                        this.setVx(0);
                        this.setVy(0);
                        // esta atraoado!!!!!
                    }
                    else if(derecha)
                    {
                        if(this.casillaactual.Derecha.Buscar(1).Dato.getId()==0|this.casillaactual.Derecha.Buscar(1).Dato.getId()==1)
                        {
                            //hay tope, se cambia de giro la imagen y la casilla a la izquierda.
                            System.out.println("ESTA DETECTANDO EL TOPE A LA DERECHA");
                            derecha = false;
                            Objeto aux = this.casillaactual.Izquierda.Buscar(1).Dato;
                            this.casillaactual.Izquierda.Buscar(1).Dato = casillaactual.Buscar(1).Dato;
                            this.casillaactual.Buscar(1).Dato = aux;
                            this.casillaactual = this.casillaactual.Izquierda;
                            this.setPosfx(this.getCordx()-75);
                            this.setVx(-5);
                            this.setVy(0);
                        }
                        else
                        {
                            System.out.println("EN EL ELSE DE DERECHA");
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
                        if(this.casillaactual.Izquierda.Buscar(1).Dato.getId()==0|this.casillaactual.Izquierda.Buscar(1).Dato.getId()==1)
                        {
                            //hay tope, se cambia de giro la imagen y la casilla a la izquierda.
                             System.out.println("ESTA DETECTANDO EL TOPE A LA IZQUIERDA");
                            derecha = true;
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
            */
        }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
        
        
    }

    
    

