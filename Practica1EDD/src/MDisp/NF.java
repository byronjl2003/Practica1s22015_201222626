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
public class NF {
    
    public int Num;
    public NCasilla Primero, Ultimo;
    public NF Next, Back;

        public NF(int num)
        {
            this.Num = num;
            this.Primero = this.Ultimo = null;
        }

        public Boolean Vacio()
        {
            return this.Primero == null;
        }
        public void Agregar(NCasilla node)
        {
            if (Vacio())
            {
                this.Primero = this.Ultimo = node;
            }
            else
            {
               
                    
                if (node.Ptrcolumna.numero  < this.Primero.Ptrcolumna.numero)
                {
                    node.Derecha = this.Primero;
                    this.Primero.Izquierda = node;
                    this.Primero = node;
                }
                else if (node.Ptrcolumna.numero > this.Primero.Ptrcolumna.numero)
                {
                    node.Izquierda = this.Ultimo;
                    this.Ultimo.Derecha = node;
                    this.Ultimo = node;
                }
                else
                {
                    NCasilla aux = this.Primero;
                    while (aux != null)
                    {
                        if (node.Ptrcolumna.numero > Primero.Ptrcolumna.numero)
                        {
                            break;
                        }
                        else
                        {
                            aux = aux.Derecha;
                        }

                    }
                    node.Derecha = aux;
                    node.Izquierda = aux.Izquierda;
                    node.Derecha.Izquierda = node;
                    node.Izquierda.Derecha = node;
                }
            }
           

        }


        public NCasilla Buscar(NC columna)
        {
            if (!Vacio())
            {
                if (this.Primero.Ptrcolumna == columna)
                {
                    return this.Primero;
                }
                else if (this.Ultimo.Ptrcolumna == columna)
                {
                    return this.Ultimo;
                }
                else
                {
                    NCasilla aux = this.Primero;
                    while (aux != null)
                    {
                        if (aux.Ptrcolumna == columna)
                            return aux;
                        else
                        {
                            aux = aux.Derecha;
                        }
                        
                        
                    }
                    return aux;
                }
            }
            else
                return null;
        }

        public String ToString()
        {
            return "F" + this.Num;
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
                    auxc = auxc.Derecha;

                
            }
            return auxc;
        }

    
}
