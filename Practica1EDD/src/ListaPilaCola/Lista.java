/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaPilaCola;

import Objetos.Objeto;

/**
 *
 * @author byron
 */
public class Lista {
    
    private  NL primero, ultimo;
    private int elementos,suelos,paredes,goombas,koopas,fichas,hongos,personajes,castillos;
    private boolean pila,cola;
    public Lista()
    {
        pila = false;
        cola = true;
        this.elementos = 0;
        this.suelos = 0;
        this.paredes = 0;
        this.goombas = 0;
        this.koopas = 0;
        this.fichas = 0;
        this.hongos = 0;
        this.personajes = 0;
        this.castillos = 0;
        this.primero= this.ultimo = null;
         if(pila)
            System.out.println("LISTA COMO PILA");
        else if(cola)
            System.out.println("LISTA COMO COLA");
        else
            System.out.println("LA LISTA NO TIENE FORMATO");
        
    }
    private boolean Vacia()
    {
        return this.getPrimero()==null;
    }
    public Objeto Siguiente()
    {
        if(pila)
            System.out.println("LISTA COMO PILA");
        else if(cola)
            System.out.println("LISTA COMO COLA");
        else
            System.out.println("LA LISTA NO TIENE FORMATO");
            
        if(Vacia())
        {
            
            return null;
        }
        else
        {
            if(pila)
        {
            return this.getUltimo().getObjeto();
        }
        else if(cola)
        {
            return this.getPrimero().getObjeto();
        }
        else
        {
            System.out.println("EN Siguiente,Esta retornando nulo");
            return null;
        }
        }
        
    }
    public void Add(Objeto obj)
    {
        if(Vacia())
        {
            this.primero = new NL(obj);
            this.ultimo = this.primero;
            this.setElementos(this.getElementos() + 1);
            ContEspecial(obj.id);
        }
        else
        {
            
            if(obj.id==6)
            {
                System.out.println("ENTRO A ID==6");
                if(this.getPersonajes()==0)
                {
                    System.out.println("ENTRO A personajes ==0");
                    ContEspecial(obj.id);
                    getUltimo().setNext(new NL(obj));
                    getUltimo().getNext().setBack(getUltimo());
                    this.setUltimo(getUltimo().getNext());
                    this.setElementos(elementos+1);
                                
                    
                }
            }
            else if(obj.id==7)
            {
                if(this.getCastillos()==0)
                {
                    ContEspecial(obj.id);
                    getUltimo().setNext(new NL(obj));
                    getUltimo().getNext().setBack(getUltimo());
                    this.setUltimo(getUltimo().getNext());
                    this.setElementos(elementos+1);                    
                }
            }
            else
            {
                ContEspecial(obj.id);
                    getUltimo().setNext(new NL(obj));
                    getUltimo().getNext().setBack(getUltimo());
                    this.setUltimo(getUltimo().getNext());
                    this.setElementos(elementos+1);
                      
            }
        
        }//else
        
        System.out.println("DESPUES DE AGREGAR: "+this.elementos);
        NL aux  = this.primero;
        while(aux!=null)
        {
            System.out.println(aux.getObjeto().nombre+",ID: "+aux.getObjeto().id);
            aux = aux.getNext();
        }
        System.out.println("EL  ULTIMO:"+ this.ultimo.getObjeto().nombre);
        System.out.println("EL  PRIMERO:"+ this.primero.getObjeto().nombre);
            
    }
    
    
   private NL EliminarFrente()
    {
        if(!Vacia())
        {
            NL eliminado = this.getPrimero();
            if(this.getPrimero()==this.getUltimo())
                this.setPrimero(this.ultimo = null);
            else
                this.setPrimero(this.getPrimero().getNext());
            return eliminado;
        }
        else
            return null;
            
            
        
    }
   
   private NL EliminarAtras()
    {
        if(!Vacia())
        {
            NL eliminado = this.getUltimo();
            if(this.getPrimero()==this.getUltimo())
                this.setPrimero(this.ultimo = null);
            else
                this.setUltimo(this.getUltimo().getBack());
            return eliminado;
        }
        else
            return null;
    }
   
   public NL Eliminar()
   {
       if(pila)
       {
           System.out.println("BORRAR COMO PILA");
           //pila = false;
           return EliminarAtras();
       }
       else if(cola)
       {
           System.out.println("BORRAR COMO COLA");
           //cola = false;
           return EliminarFrente();
       }
       else
       {
           System.out.println("NO BORRA NINGUNA");
           return null;
       }
           
   }
   
   void Descontar()
   {
       
   }
   
   public void EliminarDeLista(NL nodo)
   {
       
       if(nodo==this.primero)
       {
           if(this.primero.getNext()!=null)
               this.primero.getNext().setBack(null);
           this.primero = this.primero.getNext();
           Descontar();
           
       }
       else if(nodo==this.ultimo)
       {
           this.ultimo = this.ultimo.getBack();
           this.ultimo.setNext(null);
           Descontar();
       }
       else
       {
           NL aux = this.primero;
           while(aux!=null)
           {
                if(aux==nodo)
                {
                    aux.getBack().setNext(aux.getNext());
                    aux.getNext().setBack(aux.getBack());
                    Descontar();
                    break;
                }
                else
                {
                    aux = aux.getNext();
                }
           }
           
       }
       
   }
    
   
    




    
    private void ContEspecial(int id)
    {
        
       // this.elementos++;
        switch(id)
       {
           case 0:
           {
               this.setSuelos(this.getSuelos() + 1);
               break;
           }
           case 1:
           {
               this.setParedes(this.getParedes() + 1);
               break;
           }
           case 2:
           {
               this.setGoombas(this.getGoombas() + 1);
               break;
           }
           case 3:
           {
               this.setKoopas(this.getKoopas() + 1);
               break;
           }
           case 4:
           {
               this.setFichas(this.getFichas() + 1);
               break;
           }
           case 5:
           {
               this.setHongos(this.getHongos() + 1);
               break;
           }
           case 6:
           {
               this.setPersonajes(this.getPersonajes() + 1);
               break;
           }
           case 7:
           {
               this.setCastillos(this.getCastillos() + 1);
               break;
           }
               
           
               
               
       }
    }

    /**
     * @return the primero
     */
    public NL getPrimero() {
        return primero;
    }

    /**
     * @param primero the primero to set
     */
    public void setPrimero(NL primero) {
        this.primero = primero;
    }

    /**
     * @return the ultimo
     */
    public NL getUltimo() {
        return ultimo;
    }

    /**
     * @param ultimo the ultimo to set
     */
    public void setUltimo(NL ultimo) {
        this.ultimo = ultimo;
    }

    /**
     * @return the elementos
     */
    public int getElementos() {
        return elementos;
    }

    /**
     * @param elementos the elementos to set
     */
    public void setElementos(int elementos) {
        this.elementos = elementos;
    }

    /**
     * @return the suelos
     */
    public int getSuelos() {
        return suelos;
    }

    /**
     * @param suelos the suelos to set
     */
    public void setSuelos(int suelos) {
        this.suelos = suelos;
    }

    /**
     * @return the paredes
     */
    public int getParedes() {
        return paredes;
    }

    /**
     * @param paredes the paredes to set
     */
    public void setParedes(int paredes) {
        this.paredes = paredes;
    }

    /**
     * @return the goombas
     */
    public int getGoombas() {
        return goombas;
    }

    /**
     * @param goombas the goombas to set
     */
    public void setGoombas(int goombas) {
        this.goombas = goombas;
    }

    /**
     * @return the koopas
     */
    public int getKoopas() {
        return koopas;
    }

    /**
     * @param koopas the koopas to set
     */
    public void setKoopas(int koopas) {
        this.koopas = koopas;
    }

    /**
     * @return the fichas
     */
    public int getFichas() {
        return fichas;
    }

    /**
     * @param fichas the fichas to set
     */
    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    /**
     * @return the hongos
     */
    public int getHongos() {
        return hongos;
    }

    /**
     * @param hongos the hongos to set
     */
    public void setHongos(int hongos) {
        this.hongos = hongos;
    }

    /**
     * @return the personajes
     */
    public int getPersonajes() {
        return personajes;
    }

    /**
     * @param personajes the personajes to set
     */
    public void setPersonajes(int personajes) {
        this.personajes = personajes;
    }

    /**
     * @return the castillos
     */
    public int getCastillos() {
        return castillos;
    }

    /**
     * @param castillos the castillos to set
     */
    public void setCastillos(int castillos) {
        this.castillos = castillos;
    }

    /**
     * @return the pila
     */
    public boolean isPila() {
        return pila;
    }

    /**
     * @param pila the pila to set
     */
    public void setPila(boolean pila) {
        this.pila = pila;
    }

    /**
     * @return the cola
     */
    public boolean isCola() {
        return cola;
    }

    /**
     * @param cola the cola to set
     */
    public void setCola(boolean cola) {
        this.cola = cola;
    }
    
    
}
