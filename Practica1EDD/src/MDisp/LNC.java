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
    public int elementos;
    
    public LNC()
    {
        this.Primero = null;
        this.Ultimo = null;
        this.elementos = 0;
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
            this.elementos++;
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
                        System.out.println("EN EL WHILE DE LNC");
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
    public void Eliminar(int num)
    {
        NC elim = BuscarColumna(num);
        if(elim!=null)
        {
            if(elim==this.Primero)
            {
                if(elim.Next!=null)
                    elim.Next.Back = null;
                this.Primero = elim.Next;
            
        
            }
            else if(elim==this.Ultimo)
            {
                if(elim.Back!=null)
                    elim.Back.Next = null;
                this.Ultimo = elim.Back;
            
            }
            else
            {
                elim.Back.Next = elim.Next;
                elim.Next.Back = elim.Back;
            }
            
        }
        
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
