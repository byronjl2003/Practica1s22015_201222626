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
public class LNC {
    
    public NC Primero,Ultimo;
    public LNF ListaFilas;
    int tam;
    
    public LNC()
    {
        this.Primero = null;
        this.Ultimo = null;
        this.tam = 0;
        this.ListaFilas = new LNF();
    }
    public Boolean Vacio()
        {
            return this.Primero==null;
        }
    public NC AgregarColumna(int num)
    {
        NC Columna = BuscarColumna(num);
        if (Columna == null)
        { 
            NC nuevo = new NC(num);
                if (Vacio())
                {
                    this.Primero = this.Ultimo = nuevo;
                    return nuevo;
                }
                
                else if (this.Primero.numero  > num)
                {

                    nuevo.Next = this.Primero;
                    this.Primero.Back = nuevo;
                    this.Primero = nuevo;
                    return nuevo;
                    
                }
                else if (this.Ultimo.numero < num)
                {
                    nuevo.Back = this.Ultimo;
                    this.Ultimo.Next = nuevo;
                    this.Ultimo = nuevo;
                    return nuevo;
                }
                else
                {
                    NC aux = this.Primero;
                    while (aux != null)
                    {
                        if (aux.numero > num)
                        {
                            nuevo.Next = aux;
                            nuevo.Back = aux.Back;
                            nuevo.Next.Back = nuevo;
                            nuevo.Back.Next = nuevo;
                            return nuevo;
                        }
                        else
                        {
                            aux = aux.Next;
                        }
                    }
                    return null;
                }

            }
            else
                return Columna;
           
        }
    
    public int ConexionesPorElim(int num)
    {
        NC col = BuscarColumna(num);
        if(col!=null)
        {
            NCasilla casilla = col.Primero;
            while(casilla!=null)
            {
                if(casilla.Derecha!=null)
                    casilla.Derecha.Izquierda = casilla.Izquierda;
                if(casilla.Izquierda!=null)
                    casilla.Izquierda.Derecha = casilla.Derecha;
            }
            return 1;
        }
        else
            return 0;
            
    }
    
    

        

        public NC BuscarColumna(int num)
        {
            if (!Vacio())
            {
                if (this.Primero.numero == num)
                    return this.Primero;
                else if (this.Ultimo.numero == num)
                    return this.Ultimo;
                else
                {
                    NC aux = this.Primero;
                    while (aux != null)
                    {
                        if (aux.numero== num)
                        {
                            return aux;
                        }
                        else
                        {
                            aux = aux.Next;
                        }

                    }
                    return null;
                }
            }
            else
                return null;
        }
    
}