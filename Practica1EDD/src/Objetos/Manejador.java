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
    MDisp matriz;
    Game game;
    
    Objeto Buscarmario()
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
        Objeto obj  = this.Buscarmario();
        Mario mario = (Mario)obj;
        mario.keyPressed(e);
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
        NL node = this.lista.getPrimero();
        
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
        for(int i = 0;i<matriz.getLcolumnas().ListaFilas.elementos-3;i++)
        {
            for(int j=0;j<matriz.getLcolumnas().elementos;j++)
            {
                NC columna = matriz.getLcolumnas().BuscarColumna(j);
                NF fila = matriz.getLcolumnas().ListaFilas.Buscar(i);
                NCasilla casilla = columna.Buscar(fila);
                Objeto obj =casilla.Buscar(1).Dato; 
                if(obj!=null)
                {
                    
                    lista.Add(obj);
                    if(obj.viviente)
                    {
                        this.lvivientes.Add(obj);
                    }
                }
                
            }
        }
        
    }
    
    
 
    
}
