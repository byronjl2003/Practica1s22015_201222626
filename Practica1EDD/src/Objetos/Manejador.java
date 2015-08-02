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

/**
 *
 * @author byron
 */
public class Manejador implements Runnable {
    
    Lista lista,lvivientes;
    boolean play;
    Thread hilo;
    Graphics g;
    
    public Manejador(MDisp matriz,Graphics gg)
    {
        this.g = gg;
        lista = new Lista();
        lvivientes = new Lista();
        for(int i = 2;i<matriz.getLcolumnas().ListaFilas.elementos-2;i++)
        {
            for(int j=0;j<matriz.getLcolumnas().elementos-1;j++)
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
            
        }
        
    }
    
}
