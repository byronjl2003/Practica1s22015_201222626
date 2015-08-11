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
public class LNF {
    
    public NF Primero;

    public NF Ultimo;
    public int elementos;
    public LNF()
    {
        this.Primero = this.Ultimo = null;
        elementos = 0;
    }

    public Boolean Vacio()
    {
        return this.Primero == null;
    }
    public NF Agregar(int num)
    {
            NF fila = Buscar(num);
            if (fila == null)
            {
                elementos++;
                fila = new NF(num);
                if (Vacio())
                {
                    this.Primero = this.Ultimo = fila;
                    return fila;
                }
                else if (this.Primero.Num > num)
                {
                    fila.Next = this.Primero;
                    this.Primero.Back = fila;
                    this.Primero = fila;
                    return fila;
                }
                else if (this.Ultimo.Num < num)
                {
                    fila.Back = this.Ultimo;
                    this.Ultimo.Next = fila;
                    this.Ultimo = fila;
                    return fila;
                }
                else
                {
                    NF aux = this.Primero;
                    while (aux != null)
                    {
                        System.out.println("EN EL WHILE DE LNF");
                        if (aux.Num > num)
                        {
                            fila.Next = aux;
                            fila.Back = aux.Back;
                            fila.Next.Back = fila;
                            fila.Back.Next = fila;
                            return fila;
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
                return fila;
        }
    
    public int ConexionesPorElim(int num)
    {
        System.out.println("EN CONEXIONESPORELIM DE LNF");
        NF fila = Buscar(num);
        if(fila!=null)
        {
            NCasilla casilla = fila.Primero;
            while(casilla!=null)
            {
                if(casilla.Arriba==null)
                {
                    casilla.Ptrcolumna.Primero = casilla.Abajo;
                }
                else
                {
                    casilla.Arriba.Abajo = casilla.Abajo;
                }
                if(casilla.Abajo==null)
                {
                    casilla.Ptrcolumna.Ultimo = casilla.Arriba;
                }
                else
                {
                    casilla.Abajo.Arriba = casilla.Arriba;
                }
                    
                casilla = casilla.Derecha;
                
            }
            return 1;
        }
        else
            return 0;
            
    }
    
    public void Reenumerar()
    {
        NF fila = this.Primero;
        int conta = 0;
        while(fila!=null)
        {
            fila.Num = conta;
            conta++;
            fila = fila.Next;
        }
    }
    public void Eliminar(int num)
    {
        NF elim = Buscar(num);
        if(elim!=null)
        {
            if(elim==this.Primero)
            {
                if(elim.Next!=null)
                    elim.Next.Back = null;
                this.Primero = elim.Next;
                this.elementos--;
            
        
            }
            else if(elim==this.Ultimo)
            {
                if(elim.Back!=null)
                    elim.Back.Next = null;
                this.Ultimo = elim.Back;
                this.elementos--;
            
            }
            else
            {
                elim.Back.Next = elim.Next;
                elim.Next.Back = elim.Back;
                this.elementos--;
            }
            this.Reenumerar();
            
        }
        
    }
    
    
    
        public NF Buscar(int num)
        {
            if (!Vacio())
            {
                if (this.Primero.Num == num)
                {
                    return this.Primero;
                }
                else if (this.Ultimo.Num == num)
                {
                    return this.Ultimo;
                }
                else
                {
                    NF aux = this.Primero;
                    while (aux != null)
                    {
                        if (aux.Num == num)
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

        public StringBuilder[] GraficaHorizontal()
        {
            StringBuilder[] recolectores = new StringBuilder[4];
            recolectores[0] = new StringBuilder();
            recolectores[1] = new StringBuilder();
            recolectores[2] = new StringBuilder();
            recolectores[3] = new StringBuilder();

            String relaf = "raiz";
            NF aa = this.Primero;
            while (aa != null)
            {
                relaf = relaf + " ->" + aa.ToString();
                aa = aa.Next;
            }
            for (int i = 0; i < 4; i++)
            {
                recolectores[i].append("{\n");
                recolectores[i].append("node [shape = record ];\n");
                recolectores[i].append("[color = white];\n");
                recolectores[i].append("}\n");
                recolectores[i].append("node [shape = box ];\n");

            }




            NF aux = this.Primero;// la primera fila
            Boolean flag = false;
            while (aux != null)
            {
                if (flag)
                {
                    for (int i = 0; i < 4; i++)
                    {
                        recolectores[i].append("}\n");
                    }
                }
                    flag = true;

                    for (int i = 0; i < 4; i++)
                    {
                        recolectores[i].append("{\n");
                        recolectores[i].append("rank = same;\n");
                        NCasilla auxc = aux.TraerPrimeroPorDim(i + 1);
                        if (auxc != null)
                        {
                            recolectores[i].append(aux.ToString() + " -> " + auxc.Buscar(i+1).ToString()+auxc.Ptrcolumna.numero+ auxc.Ptrfila.Num + ";\n");
                            recolectores[i].append(auxc.Buscar(i + 1).ToString() + auxc.Ptrcolumna.numero + auxc.Ptrfila.Num + " -> " + aux.ToString() + ";\n");
                        }


                    }
                    NCasilla casillaaux = aux.Primero;
                    while (casillaaux != null)
                    {
                        int y;
                        String[] relaciones = casillaaux.relacionesH();
                        for (y = 0; y < relaciones.length; y++)
                        {
                            recolectores[y].append(relaciones[y]+"\n");


                        }


                        casillaaux = casillaaux.Derecha;


                    }

                    aux = aux.Next;

                }


            if (aux == null)
            {
                for (int i = 0; i < 4; i++)
                {
                    recolectores[i].append("}\n");
                }
            }
                return recolectores;
            }

}
