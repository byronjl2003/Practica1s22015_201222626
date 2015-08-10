/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import GUI.Game;
import ListaPilaCola.Lista;
import ListaPilaCola.NL;
import MDisp.MDisp;
import MDisp.NC;
import MDisp.NCasilla;
import MDisp.NF;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 *
 * @author byron
 */
public class Manejador{
    
    Lista lista,lvivientes;
    //boolean play;
    //Thread hilo;
    //Graphics g;
    MDisp matriz,matrizbackup;
    Game game;
    Mario mario;
    public Objeto Buscarmario()
    {
        NL nodo = this.lvivientes.getPrimero();
        while(nodo!=null)
        {
            if(nodo.getObjeto().getId()==6)
                return nodo.getObjeto();
            else
                nodo = nodo.getNext();
        }
        return null;
                
    }
    
    public void oidomario(KeyEvent e)
    {
        System.out.println("EN EL OIDO DEL MARIO");
        Objeto obj  = this.Buscarmario();
        
        if(obj!=null)
        {
            Mario mario = (Mario)obj;
            mario.keyPressed(e);
        }
        
    }
    
    public void Pintar(Graphics g)
    {
        this.tickear();
        this.renderizar(g);
    }
    public void renderizar(Graphics g)
    {
        NL node = this.lista.getPrimero();
        
        while(node!=null)
        {
            node.getObjeto().render(g,game);
            node = node.getNext();
        }
    }
    public void tickear()
    {
        NL node = this.lvivientes.getPrimero();
        
        while(node!=null)
        {
            node.getObjeto().tick();
            node = node.getNext();
        }
    }
    

    public Manejador(MDisp matriz, Game game)
    {
        
        this.game = game;
        this.matriz = matriz;
        
        lista = new Lista();
        lvivientes = new Lista();
        System.out.println("Numero de Filas En el manejador:  "+this.matriz.getLcolumnas().ListaFilas.elementos);
        System.out.println("Numero de Columnas En el manejador: "+this.matriz.getLcolumnas().elementos);
        this.primerllenado();
        if(this.matrizbackup==null)
            System.out.println("NO SIRVIO DARLE LA INSTANCIA");
        else
            System.out.println("SI SIRVIO DARLE LA INSTANCIA");
            
        
        for(int i = 0;i<this.matriz.getLcolumnas().ListaFilas.elementos-3;i++)
        {
            for(int j=0;j<this.matriz.getLcolumnas().elementos;j++)
            {
                System.out.println(i+","+j+" del contructor");
                NC columna = this.matriz.getLcolumnas().BuscarColumna(j);
                NF fila = this.matriz.getLcolumnas().ListaFilas.Buscar(i);
                NCasilla casilla = columna.Buscar(fila);
                Objeto obj =casilla.Buscar(1).Dato; 
                if(obj!=null && obj.getId()!=-1)
                {
                    
                   NL node =  this.lista.Add(obj);
                   obj.nodol = node;
                   obj.lista = this.lista;
                   System.out.println("SE INCERTO EN LISTA NORMAL"); 
                    if(obj.viviente)
                    {
                        System.out.println("SE INCERTO EN LISTA VIVIENTE"); 
                        NL nodo = this.lvivientes.Add(obj);
                        obj.nodolviv = nodo;
                        obj.listaviv = this.lvivientes;
                    }
                    if(obj.getId()==6)
                    {
                        this.mario = (Mario)obj;
                        this.mario.game = this.game;
                    }
                        
                }
                
            }
        }
        
        
    }
    
    private void primerllenado()
    {
        System.out.println("PRIMER LLENADO");
        this.matrizbackup= new MDisp();
        NF filla = this.matriz.getLcolumnas().ListaFilas.Primero;
            while(filla!=null)
            {
               
                NCasilla casilla = filla.Primero;
                while(casilla!=null)
                {
                    Objeto obj = casilla.Buscar(1).Dato;
                    switch(obj.getId())
                    {
                        case -1:
                        {
                            Vacio vacio = new Vacio();
                            vacio.setCordx(obj.getCordx());
                            vacio.setCordy(obj.getCordy());
                            vacio.setPosfx(obj.getCordx());
                            vacio.setPosfy(obj.getCordy());
                            NCasilla cas = this.matrizbackup.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,vacio);                        
                            vacio.casillaactual = cas;
                            break;
                        }
                        case 0:
                        {
                            Suelo suelo = new Suelo(obj.getNombre(),obj.getImage());
                            suelo.setCordx(obj.getCordx());
                            suelo.setCordy(obj.getCordy());
                            suelo.setPosfx(obj.getCordx());
                            suelo.setPosfy(obj.getCordy());
                            NCasilla cas = this.matrizbackup.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,suelo);                        
                            suelo.casillaactual = cas;
                            break;
                        }
                        case 1:
                        {
                            System.out.println("SE INGRESARON PARED!");
                            Pared pared = new Pared(obj.getNombre(),obj.getImage());
                            pared.setCordx(obj.getCordx());
                            pared.setCordy(obj.getCordy());
                            pared.setPosfx(obj.getCordx());
                            pared.setPosfy(obj.getCordy());
                            NCasilla cas = this.matrizbackup.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,pared);                        
                            pared.casillaactual = cas;
                            break;
                        }
                        case 2:
                        {
                            Goomba goom = new Goomba(obj.getNombre(),obj.getImage());
                            goom.setCordx(obj.getCordx());
                            goom.setCordy(obj.getCordy());
                            goom.setPosfx(obj.getCordx());
                            goom.setPosfy(obj.getCordy());
                            NCasilla cas = this.matrizbackup.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,goom);                        
                            goom.casillaactual = cas;
                            break;
                        }
                        case 3:
                        {
                            Koopa koopa = new Koopa(obj.getNombre(),obj.getImage());
                            koopa.setCordx(obj.getCordx());
                            koopa.setCordy(obj.getCordy());
                            koopa.setPosfx(obj.getCordx());
                            koopa.setPosfy(obj.getCordy());
                            NCasilla cas = this.matrizbackup.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,koopa);                        
                            koopa.casillaactual = cas;
                            break;
                        }
                        case 4:
                        {
                            Ficha ficha = new Ficha(obj.getNombre(),obj.getImage());
                            ficha.setCordx(obj.getCordx());
                            ficha.setCordy(obj.getCordy());
                            ficha.setPosfx(obj.getCordx());
                            ficha.setPosfy(obj.getCordy());
                            NCasilla cas = this.matrizbackup.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,ficha);                        
                            ficha.casillaactual = cas;
                            break;
                        }
                        case 5:
                        {
                            Hongo hongo = new Hongo(obj.getNombre(),obj.getImage());
                            hongo.setCordx(obj.getCordx());
                            hongo.setCordy(obj.getCordy());
                            hongo.setPosfx(obj.getCordx());
                            hongo.setPosfy(obj.getCordy());
                            NCasilla cas = this.matrizbackup.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,hongo);                        
                            hongo.casillaactual = cas;
                            break;
                        }
                        case 6:
                        {
                            Mario marioo = new Mario(obj.getNombre(),obj.getImage());
                            marioo.setCordx(obj.getCordx());
                            marioo.setCordy(obj.getCordy());
                            marioo.setPosfx(obj.getCordx());
                            marioo.setPosfy(obj.getCordy());
                            NCasilla cas = this.matrizbackup.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,marioo);                        
                            marioo.casillaactual = cas;
                            break;
                        }
                        case 7:
                        {
                            Castillo cast = new Castillo(obj.getNombre(),obj.getImage());
                            cast.setCordx(obj.getCordx());
                            cast.setCordy(obj.getCordy());
                            cast.setPosfx(obj.getCordx());
                            cast.setPosfy(obj.getCordy());
                            NCasilla cas = this.matrizbackup.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,cast);                        
                            cast.casillaactual = cas;
                            break;
                        }
                            
                            
                            
                            
                    }
                    
                    casilla = casilla.Derecha;
                }
                
                filla = filla.Next;
            }
            
        
            
    }
    private void segundollenado()
    {
        System.out.println("SEGUNDO LLENADO");
        this.matriz= new MDisp();
        NF filla = this.matrizbackup.getLcolumnas().ListaFilas.Primero;
        System.out.println("CUANTAS FILAS TIENE MBACKUP: "+this.matrizbackup.getLcolumnas().ListaFilas.elementos);
        System.out.println("CUANTAS COLUMNAS TIENE EL MBACKUP: "+this.matrizbackup.getLcolumnas().elementos);
            System.out.println(filla.Num+"<"+(this.matrizbackup.getLcolumnas().ListaFilas.elementos-3));
            while(filla!=null)
            {
               System.out.println("1 WHILE");
                NCasilla casilla = filla.Primero;
                while(casilla!=null)
                {
                    System.out.println("2 WHILE");
                    Objeto obj = casilla.Buscar(1).Dato;
                    switch(obj.getId())
                    {
                        
                        case -1:
                        {
                            System.out.println("SE INGRESARON VACIOS!!!!!");
                            Vacio vacio = new Vacio();
                            vacio.setCordx(obj.getCordx());
                            vacio.setCordy(obj.getCordy());
                            vacio.setPosfx(obj.getCordx());
                            vacio.setPosfy(obj.getCordy());
                            NCasilla cas = this.matriz.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,vacio);                        
                            vacio.casillaactual = cas;
                            break;
                        }
                        case 0:
                        {
                            Suelo suelo = new Suelo(obj.getNombre(),obj.getImage());
                            suelo.setCordx(obj.getCordx());
                            suelo.setCordy(obj.getCordy());
                            suelo.setPosfx(obj.getCordx());
                            suelo.setPosfy(obj.getCordy());
                            NCasilla cas = this.matriz.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,suelo);                        
                            suelo.casillaactual = cas;
                            break;
                        }
                        case 1:
                        {
                            System.out.println("SE INGRESARON PAREDES!");
                            Pared pared = new Pared(obj.getNombre(),obj.getImage());
                            pared.setCordx(obj.getCordx());
                            pared.setCordy(obj.getCordy());
                            pared.setPosfx(obj.getCordx());
                            pared.setPosfy(obj.getCordy());
                            NCasilla cas = this.matriz.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,pared);                        
                            pared.casillaactual = cas;
                            break;
                        }
                        case 2:
                        {
                            Goomba goom = new Goomba(obj.getNombre(),obj.getImage());
                            goom.setCordx(obj.getCordx());
                            goom.setCordy(obj.getCordy());
                            goom.setPosfx(obj.getCordx());
                            goom.setPosfy(obj.getCordy());
                            NCasilla cas = this.matriz.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,goom);                        
                            goom.casillaactual = cas;
                            break;
                        }
                        case 3:
                        {
                            Koopa koopa = new Koopa(obj.getNombre(),obj.getImage());
                            koopa.setCordx(obj.getCordx());
                            koopa.setCordy(obj.getCordy());
                            koopa.setPosfx(obj.getCordx());
                            koopa.setPosfy(obj.getCordy());
                            NCasilla cas = this.matriz.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,koopa);                        
                            koopa.casillaactual = cas;
                            break;
                        }
                        case 4:
                        {
                            Ficha ficha = new Ficha(obj.getNombre(),obj.getImage());
                            ficha.setCordx(obj.getCordx());
                            ficha.setCordy(obj.getCordy());
                            ficha.setPosfx(obj.getCordx());
                            ficha.setPosfy(obj.getCordy());
                            NCasilla cas = this.matriz.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,ficha);                        
                            ficha.casillaactual = cas;
                            break;
                        }
                        case 5:
                        {
                            Hongo hongo = new Hongo(obj.getNombre(),obj.getImage());
                            hongo.setCordx(obj.getCordx());
                            hongo.setCordy(obj.getCordy());
                            hongo.setPosfx(obj.getCordx());
                            hongo.setPosfy(obj.getCordy());
                            NCasilla cas = this.matriz.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,hongo);                        
                            hongo.casillaactual = cas;
                            break;
                        }
                        case 6:
                        {
                            Mario marioo = new Mario(obj.getNombre(),obj.getImage());
                            marioo.setCordx(obj.getCordx());
                            marioo.setCordy(obj.getCordy());
                            marioo.setPosfx(obj.getCordx());
                            marioo.setPosfy(obj.getCordy());
                            NCasilla cas = this.matriz.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,marioo);                        
                            marioo.casillaactual = cas;
                            break;
                        }
                        case 7:
                        {
                            Castillo cast = new Castillo(obj.getNombre(),obj.getImage());
                            cast.setCordx(obj.getCordx());
                            cast.setCordy(obj.getCordy());
                            cast.setPosfx(obj.getCordx());
                            cast.setPosfy(obj.getCordy());
                            NCasilla cas = this.matriz.Insertar(casilla.Ptrfila.Num,casilla.Ptrcolumna.numero,1,cast);                        
                            cast.casillaactual = cas;
                            break;
                        }
                            
                            
                            
                            
                    }
                    
                    casilla = casilla.Derecha;
                }
                
                filla = filla.Next;
            }
            
        
    }
    public void Reiniciar()
    {
        System.out.println("EN REINICIAR");
        this.game.play = false;
        if(this.mario!=null)
            this.mario.bloqueo = true;
        //this.game.play = false;
        this.segundollenado();
        lista = new Lista();
        lvivientes = new Lista();
        for(int i = 0;i<this.matriz.getLcolumnas().ListaFilas.elementos-3;i++)
        {
            for(int j=0;j<this.matriz.getLcolumnas().elementos;j++)
            {
                System.out.println(i+","+j);
                NC columna = this.matriz.getLcolumnas().BuscarColumna(j);
                NF fila = this.matriz.getLcolumnas().ListaFilas.Buscar(i);
                NCasilla casilla = columna.Buscar(fila);
                Objeto obj =casilla.Buscar(1).Dato; 
                if(obj!=null && obj.getId()!=-1)
                {
                   System.out.println("SEGUNDO LLENADO,SE INCERTO EN LISTA NORMAL"); 
                   NL node =  this.lista.Add(obj);
                   obj.nodol = node;
                   obj.lista = this.lista;
                   
                    if(obj.viviente)
                    {
                        System.out.println("SEGUNDO LLENADO,SE INCERTO EN LISTA VIVIENTES"); 
                        NL nodo = this.lvivientes.Add(obj);
                        obj.nodolviv = nodo;
                        obj.listaviv = this.lvivientes;
                    }
                    if(obj.getId()==6)
                        this.mario = (Mario)obj;
                }
                
            }
        }
        this.game.play = true;
    }
    
    
 
    
}
