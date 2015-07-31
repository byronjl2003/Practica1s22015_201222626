package MDisp;

import Objetos.Objeto;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author byron
 */
public class MDisp {
    
    private LNC Lcolumnas;

        public MDisp()
        {
            this.Lcolumnas = new LNC();

        }
        public Boolean Vacio()
        {
            return this.Lcolumnas.Vacio();
        }

        public NCasilla Insertar(int col,int fil,int dimension,Objeto dato)
        {
            NC Columna = this.Lcolumnas.AgregarColumna(col);
            NF Fila = this.Lcolumnas.ListaFilas.Agregar(fil);
            NCasilla casilla = Columna.Agregar(dato,dimension,Fila);
            return casilla;
           
        }
        public int Eliminar(int num, int flag)
        {
            if(flag==0)
            {
                
                int resp =  this.Lcolumnas.ListaFilas.ConexionesPorElim(num);
                this.Lcolumnas.ListaFilas.Eliminar(num);
                return resp;
            }
            else if(flag==1)
            {
                int resp =  this.Lcolumnas.ConexionesPorElim(num);
                this.Lcolumnas.Eliminar(num);
                return resp;
            }
            return -1;
            
        }

        public void graficarMDispersa()
        {
            FileWriter fichero = null;
            PrintWriter pw = null;
            try
            {
                fichero = new FileWriter("/home/byron/MDisp.txt");
                pw = new PrintWriter(fichero);
                pw.println("digraph g{");
                pw.println("ranksep = .5;splines=ortho;");
                pw.println("{");
                pw.println("node[shape = record]");
                String primera ="";
                NC primero = this.Lcolumnas.Primero;
                NCasilla frs = primero.Primero;
                while(frs!=null)
                {
                    NM casilla = frs.Buscar(1);
                    NCasilla aux2 = frs.Izquierda;
                    while(aux2.Ptrfila==null)
                    {
                        aux2 = aux2.Izquierda;
                    }
                    
                    primera = primera + "Nodo"+primero.numero+aux2.Ptrfila.Num+"[label = \""+"<f0>|<f1>"+casilla.ToString()+" |<f2>"+"\"]";
                    frs = frs.Abajo;
                }
 
            } catch (Exception e) {
                e.printStackTrace();
            } finally
            {
                try {
           
                        if (null != fichero)
                                fichero.close();
                    } catch (Exception e2) {
                            e2.printStackTrace();
                                            }
            }

        }

        }

    

