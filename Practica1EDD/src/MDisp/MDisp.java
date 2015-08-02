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
            return this.getLcolumnas().Vacio();
        }

        public NCasilla Insertar(int fil,int col,int dimension,Objeto dato)
        {
           // System.out.println("SE VA A INSERTAR EN "+fil+","+col);
            NC Columna = this.getLcolumnas().AgregarColumna(col);
            NF Fila = this.getLcolumnas().ListaFilas.Agregar(fil);
            NCasilla casilla = Columna.Agregar(dato,dimension,Fila);
            return casilla;
           
        }
        public int Eliminar(int num, int flag)
        {
            if(flag==0)
            {
                
                int resp =  this.getLcolumnas().ListaFilas.ConexionesPorElim(num);
                this.getLcolumnas().ListaFilas.Eliminar(num);
                return resp;
            }
            else if(flag==1)
            {
                int resp =  this.getLcolumnas().ConexionesPorElim(num);
                this.getLcolumnas().Eliminar(num);
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
                //***el primer segmento, la declaracion de todos los nodos
                NC primero = this.getLcolumnas().Primero;
                while(primero!=null)
                {
                    int contf=1;
                    NCasilla frs = primero.Primero;
                    while(frs!=null)
                    {
                        if(primero==this.getLcolumnas().Ultimo)
                            pw.println("Nulo"+primero.numero+contf+"[label=\"NULO!\"];");
                            
                        NM casilla = frs.Buscar(1);
                    
                        if(frs==primero.Ultimo)
                        primera = primera + "Nodo"+primero.numero+""+contf+"[label = \""+"<f0>|<f1>"+casilla.ToString()+" |<f2>"+"\"];";
                        else
                            primera = primera + "Nodo"+primero.numero+""+contf+"[label = \""+"<f0>|<f1>"+casilla.ToString()+" |<f2>"+"\"];";
                        pw.println(primera);
                        frs = frs.Abajo;
                        contf++;
                    }
 
                    
                    primero = primero.Next;
                   
                }
                //****segundo segmento,las relaciones de la primera columna;
                pw.println("ranksep = .5;splines=ortho;");
                pw.println("{");
                    int contff = 1;
                    NC columna = this.getLcolumnas().Primero;
                    NCasilla aux = columna.Primero;
                    while(aux!=null)
                    {
                        String normal="";
                        if(aux.Abajo==null)
                             normal=normal +"Nodo"+columna.numero+""+contff+"-> NULO;";
                        else
                        {
                            int c = contff+1;
                            normal = normal+"Nodo"+columna.numero+""+contff+" -> Nodo"+columna+""+c+";";
                        }
                        pw.println("normal");
                      aux = aux.Abajo;
                      contff++;
                    }
                    aux = columna.Ultimo;
                    while(aux!=null)
                    {
                        contff = this.getLcolumnas().ListaFilas.elementos;
                        String anormal="";
                        if(aux.Arriba==null)
                             anormal=anormal +"Nodo"+columna.numero+""+contff+"-> NULO;";
                        else
                        {
                            int c = contff-1;
                            anormal = anormal+"Nodo"+columna.numero+""+contff+" -> Nodo"+columna+""+c+";";
                        }
                        pw.println("normal");
                        aux = aux.Arriba;
                        contff--;
                    }
                pw.println("}");
                    
                //** tercer segmento,todas las relaciones horizontales
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

    /**
     * @return the Lcolumnas
     */
    public LNC getLcolumnas() {
        return Lcolumnas;
    }

    /**
     * @param Lcolumnas the Lcolumnas to set
     */
    public void setLcolumnas(LNC Lcolumnas) {
        this.Lcolumnas = Lcolumnas;
    }

        }

    

