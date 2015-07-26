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
    public NC AgregarColumna(String letra)
    {
        NC Columna = BuscarColumna(letra);
        if (Columna == null)
        { 
            NC nuevo = new NC(letra);
                if (Vacio())
                {
                    this.Primero = this.Ultimo = nuevo;
                    return nuevo;
                }
                
                else if (this.Primero.Letra.compareTo(letra)  > 0)
                {

                    nuevo.Next = this.Primero;
                    this.Primero.Back = nuevo;
                    this.Primero = nuevo;
                    return nuevo;
                    
                }
                else if (this.Ultimo.Letra.compareTo(letra) < 0)
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
                        if (aux.Letra.compareTo(letra) > 0)
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

        

        public NC BuscarColumna(String letra)
        {
            if (!Vacio())
            {
                if (this.Primero.Letra.compareTo(letra) == 0)
                    return this.Primero;
                else if (this.Ultimo.Letra.compareTo(letra) == 0)
                    return this.Ultimo;
                else
                {
                    NC aux = this.Primero;
                    while (aux != null)
                    {
                        if (aux.Letra.compareTo(letra)== 0)
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
