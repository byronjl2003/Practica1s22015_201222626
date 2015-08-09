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
            System.out.println("EN graficarMDispersa");
            StringBuilder constructor  = new StringBuilder();
            constructor.append("digraph g{\n");
            constructor.append("node[shape = record]\n");
            NC primero = this.getLcolumnas().Primero;
            while(primero!=null)
            {
                NF fila = this.getLcolumnas().ListaFilas.Primero;
                while(fila!=null)
                {
                    
                    constructor.append(primero.Buscar(fila).ToStringLabel());
                    fila = fila.Next;
                    
                }
                primero = primero.Next;
                
            }
            constructor.append("ranksep = .5; splines=ortho;\n{\n");
            NC columna = this.getLcolumnas().Primero;
            NF fila = this.getLcolumnas().ListaFilas.Primero;
            while(fila!=null)
            {
                if(fila.Next!=null)
                    constructor.append(columna.Buscar(fila).ToString()+"->");
                else
                    constructor.append(columna.Buscar(fila).ToString()+"[color=white];\n");
                fila = fila.Next;
                    
            }
            
            constructor.append("}\n");
            
            NF filla = this.getLcolumnas().ListaFilas.Primero;
            while(filla!=null)
            {
                constructor.append("{\nrank = same;\n");
                NCasilla casilla = filla.Primero;
                while(casilla!=null)
                {
                    if(casilla.Izquierda!=null)
                        constructor.append(casilla.ToString()+":f0 -> "+casilla.Izquierda.ToString()+":f2;\n");
                    if(casilla.Derecha!=null)
                        constructor.append(casilla.ToString()+":f2 -> "+casilla.Derecha.ToString()+":f0;\n");
                   
                        
                    casilla = casilla.Derecha;
                }
                constructor.append("}\n");
                filla = filla.Next;
            }
            
            NF fi = this.Lcolumnas.ListaFilas.Primero;
            while(fi!=null)
            {
                NCasilla cas = fi.Primero;
                while(cas!=null)
                {
                    
                    if(cas.Arriba!=null)
                    {
                        constructor.append(cas.ToString()+" -> "+cas.Arriba.ToString()+";\n");
                       // constructor.append(cas.Arriba.ToString()+" -> "+cas.ToString()+";\n");
                    }
                    if(cas.Abajo!=null)
                    {
                        constructor.append(cas.ToString()+" -> "+cas.Abajo.ToString()+";\n");
                        ///constructor.append(cas.Abajo.ToString()+" -> "+cas.ToString()+";\n");
                    }
                    cas = cas.Derecha;
                }
                fi = fi.Next;
            }
            
            constructor.append("}\n");
           //************************************************
            FileWriter fichero = null;
            PrintWriter pw = null;
            
            try
            {
                fichero = new FileWriter("/home/byron/GraficasMarioMaker/"+"matriz"+".dot");
                pw = new PrintWriter(fichero);
 
            
                pw.println(constructor.toString());
 
            } catch (Exception e)
            {
                e.printStackTrace();
            } 
            finally
            {
                try
                {
                    if (null != fichero)
                        fichero.close();
                }
                catch (Exception e2)
                {
                    e2.printStackTrace();
                }
           
           //....GENERACION CON DOT
                try
		{       
			ProcessBuilder pbuilder;
		    
			/*
			 * Realiza la construccion del comando    
			 * en la linea de comandos esto es: 
			 * dot -Tpng -o archivo.png archivo.dot
			 */
			pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "/home/byron/GraficasMarioMaker/matriz.png", "/home/byron/GraficasMarioMaker/matriz.dot" );
			pbuilder.redirectErrorStream( true );
			//Ejecuta el proceso
			pbuilder.start();
		    
		} catch (Exception e) { e.printStackTrace(); }
        }
            
            
        }
        
        
        
        
        
        
        
        
        
        
         public void graficarMDispersa2()
        {
            System.out.println("EN graficarMDispersa2!!!!");
            StringBuilder constructor  = new StringBuilder();
            constructor.append("digraph g{\n");
            constructor.append("node[shape = record]\n");
            NC primero = this.getLcolumnas().Primero;
            while(primero!=null)
            {
                NF fila = this.getLcolumnas().ListaFilas.Buscar(0);
                while(fila.Num<this.getLcolumnas().ListaFilas.elementos-2)
                {
                    
                    constructor.append(primero.Buscar(fila).ToStringLabel());
                    fila = fila.Next;
                    
                }
                primero = primero.Next;
                
            }
            constructor.append("ranksep = .5; splines=ortho;\n{\n");
            NC columna = this.getLcolumnas().Primero;
            NF fila = this.getLcolumnas().ListaFilas.Buscar(0);
            while(fila.Num<this.getLcolumnas().ListaFilas.elementos-3)
            {
                if(fila.Num == this.getLcolumnas().ListaFilas.elementos-4)
                    constructor.append(columna.Buscar(fila).ToString()+"[color=white];\n");
                
                else if(fila.Next!=null)
                    constructor.append(columna.Buscar(fila).ToString()+"->");
                
                fila = fila.Next;
                    
            }
            
            constructor.append("}\n");
            
            NF filla = this.getLcolumnas().ListaFilas.Buscar(0);
            while(filla.Num<this.getLcolumnas().ListaFilas.elementos-3)
            {
                constructor.append("{\nrank = same;\n");
                NCasilla casilla = filla.Primero;
                while(casilla!=null)
                {
                    if(casilla.Izquierda!=null)
                        constructor.append(casilla.ToString()+":f0 -> "+casilla.Izquierda.ToString()+":f2;\n");
                    if(casilla.Derecha!=null)
                        constructor.append(casilla.ToString()+":f2 -> "+casilla.Derecha.ToString()+":f0;\n");
                   
                        
                    casilla = casilla.Derecha;
                }
                constructor.append("}\n");
                filla = filla.Next;
            }
            
            NF fi = this.getLcolumnas().ListaFilas.Buscar(0);
            while(fi.Num<this.getLcolumnas().ListaFilas.elementos-3)
            {
                NCasilla cas = fi.Primero;
                while(cas!=null)
                {
                    
                    if(cas.Arriba!=null)
                    {
                        if(cas.Arriba.Ptrfila.Num>=0)
                            constructor.append(cas.ToString()+" -> "+cas.Arriba.ToString()+";\n");
                       // constructor.append(cas.Arriba.ToString()+" -> "+cas.ToString()+";\n");
                    }
                    if(cas.Abajo!=null)
                    {
                        if(cas.Abajo.Ptrfila.Num<=this.getLcolumnas().ListaFilas.elementos-4)
                            constructor.append(cas.ToString()+" -> "+cas.Abajo.ToString()+";\n");
                        ///constructor.append(cas.Abajo.ToString()+" -> "+cas.ToString()+";\n");
                    }
                    cas = cas.Derecha;
                }
                fi = fi.Next;
            }
            
            constructor.append("}\n");
           //************************************************
            FileWriter fichero = null;
            PrintWriter pw = null;
            
            try
            {
                fichero = new FileWriter("/home/byron/GraficasMarioMaker/"+"matriz"+".dot");
                pw = new PrintWriter(fichero);
 
            
                pw.println(constructor.toString());
 
            } catch (Exception e)
            {
                e.printStackTrace();
            } 
            finally
            {
                try
                {
                    if (null != fichero)
                        fichero.close();
                }
                catch (Exception e2)
                {
                    e2.printStackTrace();
                }
           
           //....GENERACION CON DOT
                try
		{       
			ProcessBuilder pbuilder;
		    
			/*
			 * Realiza la construccion del comando    
			 * en la linea de comandos esto es: 
			 * dot -Tpng -o archivo.png archivo.dot
			 */
			pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "/home/byron/GraficasMarioMaker/matriz.png", "/home/byron/GraficasMarioMaker/matriz.dot" );
			pbuilder.redirectErrorStream( true );
			//Ejecuta el proceso
			pbuilder.start();
		    
		} catch (Exception e) { e.printStackTrace(); }
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

    

