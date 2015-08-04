/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import ListaPilaCola.Lista;
import ListaPilaCola.NL;
import MDisp.MDisp;
import MDisp.NC;
import MDisp.NCasilla;
import MDisp.NF;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author byron
 */
public class Manejador implements Runnable {
    
    Lista lista,lvivientes;
    boolean play;
    Thread hilo;
    Graphics g;
    MDisp matriz;
    JPanel lienzo;
    public void renderizar()
    {
        NL node = this.lista.getPrimero();
        
        while(node!=null)
        {
            node.getObjeto().render(g);
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
    public Manejador(MDisp matriz,Graphics gg,JPanel lien)
    {
        play = true;
        this.lienzo = lien;
        this.matriz = matriz;
        this.g = gg;
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
                    obj.lienzo = this.lienzo;
                    lista.Add(obj);
                    if(obj.viviente)
                    {
                        this.lvivientes.Add(obj);
                    }
                }
                
            }
        }
        hilo = new Thread(this,"manejador");
    }
    
    public void Stop()
    {
        this.play = false;
    }
    public void Start()
    {
        this.play = true;
    }

    @Override
    public void run()
    {
        try
        {
            while(play)
            {
                System.out.println("EN EL WHILE INFINITO DEL GAME");
                NL primero = this.lvivientes.getPrimero();
                while(primero!=null)
                {
                    primero.getObjeto().tick();
                    primero.getObjeto().render(this.g);
                    primero = primero.getNext();
                }
                    
                
                Thread.sleep(10);
            }
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        
    }
    
}
