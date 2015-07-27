package MDisp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author byron
 */
public class NC {
    
    public int  numero;
    public NCasilla Primero;
    public NCasilla Ultimo;
    public NC Next;
    public NC Back;
    public NC(int num)
    {
        this.numero = num;
        this.Primero = this.Ultimo =null;
    }
    public Boolean Vacia()
    {
        return this.Primero == null;
    }

    public NCasilla Buscar(NF fila)
    {
        if (!Vacia())
        {
            if (this.Primero.Ptrfila == fila)
            {
                    return this.Primero;
                }
                else if (this.Ultimo.Ptrfila == fila)
                {
                    return this.Ultimo;
                }
                else
                {
                    NCasilla aux = this.Primero;
                    while (aux != null)
                    {
                        if (aux.Ptrfila == fila)
                            return aux;
                        else
                            aux = aux.Abajo;
                    }
                    return null;
                }
            }
            else
                return null;
            
        }
        public NCasilla Agregar(String dato, int dimension,NF fila)
        {
            NCasilla casilla = Buscar(fila);
            if ( casilla == null)// no existe la casilla central
            {
                casilla = new NCasilla(this,fila);
                casilla.Agregar(dato,dimension);
                if (Vacia())
                {
                    this.Primero = this.Ultimo = casilla;
                    fila.Agregar(casilla);
                    return casilla;
                    
                }
                else
                {
                    if (casilla.Ptrfila.Num < this.Primero.Ptrfila.Num)// se agrega como primero por la fila
                    {
                        casilla.Abajo = this.Primero;
                        this.Primero.Arriba = casilla;
                        this.Primero = casilla;
                        fila.Agregar(casilla);
                        return casilla;
                    }
                    else if (casilla.Ptrfila.Num > this.Ultimo.Ptrfila.Num)
                    {
                        casilla.Arriba = this.Ultimo;
                        this.Ultimo.Abajo = casilla;
                        this.Ultimo = casilla;
                        fila.Agregar(casilla);
                        return casilla;
                    }
                    else// se va a ingresar entre dos casillas
                    {
                        NCasilla aux = this.Primero;
                        while (aux != null)
                        {
                            if (aux.Ptrfila.Num>casilla.Ptrfila.Num)// se localiza el nodo en la columna que vaya abajo
                                break;
                            else
                                aux = aux.Abajo;
                        }
                        casilla.Abajo = aux;
                        casilla.Arriba = aux.Arriba;
                        casilla.Abajo.Arriba = casilla;
                        casilla.Arriba.Abajo = casilla;
                        fila.Agregar(casilla);
                        return casilla;

                    }
                }
                
            }
            else// si existe la casilla central, hay que meter el dato a la casilla si es que su dimension no esta ocupada
            {
                casilla.Agregar(dato,dimension);
                return casilla;
            }


        }


        public NCasilla TraerPrimeroPorDim(int dim)
        {
            NM au = null;
            NCasilla auxc = this.Primero;
            while (auxc != null)
            {
                au = auxc.Buscar(dim);
                if (au != null)
                    return auxc;
                else
                    auxc = auxc.Abajo;


            }
            return auxc;
        }

        public String ToString()
        {
            return "C_" + this.numero;
        }

    
}
