package MDisp;

import Objetos.Objeto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author byron
 */
public class NCasilla {
    
    public NF Ptrfila;
    public NC Ptrcolumna;
    public int Dimcentral;
    public NM Primero;
    public NM Ultimo;
    public NCasilla Arriba, Abajo, Derecha, Izquierda;
    public NCasilla(NC columna,NF fila)
    {
        this.Primero = this.Ultimo =  null;
        this.Arriba=null;
        this.Abajo = null;
        this.Derecha = null;
        this.Izquierda = null;
        this.Ptrcolumna = columna;
        this.Ptrfila = fila;

    }

    public void Agregar(Objeto dato, int dimension)
    {
        if (Buscar(dimension) == null)
        {
            NM nodo = new NM(dato,dimension);
            if (Vacia())
            {
                this.Primero = this.Ultimo = nodo;
                    
            }
            else
            {
                if (dimension < this.Primero.Dimension)
                {
                    nodo.Next = this.Primero;
                    this.Primero.Back = nodo;
                    this.Primero = nodo;
                }
                else if (dimension > this.Ultimo.Dimension)
                {
                    nodo.Back = this.Ultimo;
                    this.Ultimo.Next = nodo;
                    this.Ultimo = nodo;
                }
                else
                {
                    NM aux = this.Primero;
                    while (aux != null)
                    {
                        if (aux.Dimension > dimension)
                        {
                            break;
                        }
                        else
                        {
                            aux = aux.Next;
                        }
                                
                    }
                    nodo.Next = aux;
                    nodo.Back = aux.Back;
                    nodo.Next.Back = nodo;
                    nodo.Back.Next = nodo;
                }
            }
        }
            

    }
    public Boolean Vacia()
    {
        return this.Primero == null;
    }

    public NM Buscar(int dim)
    {
        if (!Vacia())
        {
            if (dim == this.Primero.Dimension)
                return this.Primero;
            else if (dim == this.Ultimo.Dimension)
                return this.Ultimo;
            else
            {
                NM aux = this.Primero;
                while (aux != null)
                {
                    if (dim == aux.Dimension)
                    {
                        return aux;
                    }
                    aux = aux.Next;
                }
                return aux;
            }
        }
        else
        {
            return null;
        }
            
    }

    public String[] relacionesH()
    {
        String [] relations = new String[4];
        relations[0] = "";
        relations[1] = "";
        relations[2] = "";
        relations[3] = "";
        NM aux = this.Primero;
        while (aux != null)
        {
            if (this.Derecha != null)
            {

                NCasilla casillaaux = this.Derecha;
                while (casillaaux != null)
                {

                    NM aux2 = casillaaux.Buscar(aux.Dimension);
                    if (aux2 != null)
                    {
                        relations[aux.Dimension - 1] = relations[aux.Dimension - 1] + aux.ToString() + this.Ptrcolumna.numero + this.Ptrfila.Num + " -> " + aux2.ToString() + casillaaux.Ptrcolumna.numero + casillaaux.Ptrfila.Num + "; " + aux2.ToString() + casillaaux.Ptrcolumna.numero + casillaaux.Ptrfila.Num + " -> " + aux.ToString() + this.Ptrcolumna.numero + this.Ptrfila.Num + ";";
                        break;
                    }
                    else
                    {
                            
                        casillaaux = casillaaux.Derecha;
                    }



                }

            }
            aux = aux.Next;

            }
           
            return relations;
        }



        public String[] relacionesV()
        {
            String[] relations = new String[4];
            relations[0] = "";
            relations[1] = "";
            relations[2] = "";
            relations[3] = "";
            NM aux = this.Primero;
            while (aux != null)
            {

                if (this.Abajo != null)
                {
                    NCasilla casillaaux = this.Abajo;
                    while (casillaaux != null)
                    {

                        NM aux2 = casillaaux.Buscar(aux.Dimension);
                        if (aux2 != null)
                        {
                            relations[aux.Dimension - 1] = relations[aux.Dimension - 1] + aux.ToString() + this.Ptrcolumna.numero + this.Ptrfila.Num + " -> " + aux2.ToString() + casillaaux.Ptrcolumna.numero + casillaaux.Ptrfila.Num + "; " + aux2.ToString() + casillaaux.Ptrcolumna.numero + casillaaux.Ptrfila.Num + " -> " + aux.ToString() + this.Ptrcolumna.numero + this.Ptrfila.Num + ";";
                            break;
                        }
                        else
                        {
                            casillaaux = casillaaux.Abajo;
                        }


                    }
                }
                

                aux = aux.Next;
            }
            return relations;
        }
        public String ToStringLabel()
        { 
            //if(this.Primero.Dato==null)
              //  System.out.println("EL DATO ES NULO!!!!!!!!!!!!!!!!!!!!!!!");
            if(this.Primero.Dato.getId()==-1)
            {
                return this.ToString() + "[label=\"<f0>|<f1>[VACIO]|<f2>\"];\n";
            }
            else
            {
                return this.ToString()+"[label=\"<f0>|<f1>TIPO:"+this.Primero.Dato.ToString()+"\\nNOMBRE:"+this.Primero.Dato.getNombre()+"|<f2>\"];\n";
            
            }
        
           // return "ERROR!!!!!";
            
        }
        public String ToString()
        {
            return "Casilla"+this.Ptrfila.Num+this.Ptrcolumna.numero;
        }

    
    
}
