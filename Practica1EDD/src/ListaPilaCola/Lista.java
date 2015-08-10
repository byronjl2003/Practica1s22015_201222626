/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaPilaCola;

import Objetos.Objeto;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author byron
 */
public class Lista {
    
    private  NL primero, ultimo;
    private int elementos,suelos,paredes,goombas,koopas,fichas,hongos,personajes,castillos;
    int ids = 0;
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
        
    }
    private boolean Vacia()
    {
        return this.getPrimero()==null;
    }
    public Objeto Siguiente()
    {
        
            
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
    public NL Add(Objeto obj)
    {
        if(Vacia())
        {
            
            this.setElementos(this.getElementos() + 1);
            this.primero = new NL(obj,this.ids);
            ids++;
            this.ultimo = this.primero;
            ContEspecial(obj.getId());
            return this.primero;
        }
        else
        {
            
            if(obj.getId()==6)
            {
                //System.out.println("ENTRO A ID==6");
                if(this.getPersonajes()==0)
                {
                    //System.out.println("ENTRO A personajes ==0");
                    ContEspecial(obj.getId());
                    NL nodo = new NL(obj,ids);
                    ids++;
                    this.setElementos(elementos+1);
                    this.ultimo.setNext(nodo);
                    nodo.setBack(this.ultimo);
                    this.ultimo = nodo;
                    return nodo;            
                    
                }
                else
                    return null;
                    
            }
            else if(obj.getId()==7)
            {
                if(this.getCastillos()==0)
                {
                    ContEspecial(obj.getId());
                    
                    this.setElementos(elementos+1);
                    NL nodo = new NL(obj,this.ids);
                    ids++;
                    this.ultimo.setNext(nodo);
                    nodo.setBack(this.ultimo);
                    this.ultimo = nodo;
                    return nodo;
                }
                else
                    return null;
            }
            else
            {
                    ContEspecial(obj.getId());
                    
                    this.setElementos(elementos+1);
                    NL nodo = new NL(obj,ids);
                    ids++;
                    this.ultimo.setNext(nodo);
                    nodo.setBack(this.ultimo);
                    this.ultimo = nodo;
                    return nodo;
            }
        
        }//else
                    
    }
    
    
   private NL EliminarFrente()
    {
        if(!Vacia())
        {
            NL eliminado = this.getPrimero();
            this.Descontar(eliminado.getObjeto().getId());
            if(this.getPrimero()==this.getUltimo())
                this.setPrimero(this.ultimo = null);
            else
                this.setPrimero(this.getPrimero().getNext());
            this.elementos--;
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
            this.Descontar(eliminado.getObjeto().getId());
            if(this.getPrimero()==this.getUltimo())
                this.setPrimero(this.ultimo = null);
            else
                this.setUltimo(this.getUltimo().getBack());
            this.elementos--;
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
   
   void Descontar(int id)
   {
       switch(id)
       {
           case 0:
           {
               this.suelos--;;
               break;
           }
           case 1:
           {
               this.paredes--;
               break;
           }
           case 2:
           {
               this.goombas--;
               break;
           }
           case 3:
           {
               this.koopas--;
               break;
           }
           case 4:
           {
               this.fichas--;
               break;
           }
           case 5:
           {
               this.hongos--;
               break;
           }
           case 6:
           {
               this.personajes--;
               break;
           }
           case 7:
           {
               this.castillos--;
               break;
           }
   }
   }
   
   public void EliminarDeLista(NL nodo)
   {
       
       if(nodo==this.primero)
       {
           if(this.primero.getNext()!=null)
               this.primero.getNext().setBack(null);
           this.primero = this.primero.getNext();
           Descontar(nodo.getObjeto().getId());
           
       }
       else if(nodo==this.ultimo)
       {
           this.ultimo = this.ultimo.getBack();
           this.ultimo.setNext(null);
           Descontar(nodo.getObjeto().getId());
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
                    Descontar(nodo.getObjeto().getId());
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
    
    public StringBuilder Restantes()
    {
        StringBuilder resp = new StringBuilder();
        resp.append("Personajes: "+this.personajes+"\n");
        resp.append("Castillos: "+this.castillos+"\n");
        resp.append("Goombas: "+this.goombas+"\n");
        resp.append("Koopas: "+this.koopas+"\n");
        resp.append("Suelos: "+this.suelos+"\n");
        resp.append("Paredes: "+this.paredes+"\n");
        resp.append("Hongos: "+this.hongos+"\n");
        resp.append("Fichas: "+this.fichas+"\n");
        return resp;
    }
    public void Graficar(String nombre)
    {
        System.out.println("EN GRAFICAR DE LA LISTA");
        StringBuilder constructor  = new StringBuilder();
        constructor.append("digraph g{ \n");
        constructor.append("node [shape = record ];\n");
        //----encabezado de usuarios
        NL aux = this.primero;
        while(aux!=null)
        {
            constructor.append(aux.ToStringEncabezado()+"\n");
            aux = aux.getNext();
        }
        //-- ralaciones entre usuarios
        NL aux2 = this.primero;
        while(aux2!=null)
        {
            if(aux2.getNext()!=null)
                constructor.append(aux2.ToString()+":f2 -> "+aux2.getNext().ToString()+":f0 ;\n");
            if(aux2.getBack()!=null)
                constructor.append(aux2.ToString()+":f0 -> "+aux2.getBack().ToString()+":f2 ;\n");
            aux2 = aux2.getNext();
        }
       
        //-- se grafica los string en cada usuario
        
        
        
        constructor.append("\n }");
        
        
        
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("C:\\Users\\byron\\Desktop\\GraficasMarioMaker\\"+nombre+".dot");
            pw = new PrintWriter(fichero);
 
            
                pw.println(constructor.toString());
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
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
			pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "C:\\Users\\byron\\Desktop\\GraficasMarioMaker/Lista.png", "C:\\Users\\byron\\Desktop\\GraficasMarioMaker\\Lista.dot" );
			pbuilder.redirectErrorStream( true );
			//Ejecuta el proceso
			pbuilder.start();
		    
		} catch (Exception e) { e.printStackTrace(); }
        }
        
        
        
    }
    
    

  
    public NL getPrimero() {
        return primero;
    }

  
    public void setPrimero(NL primero) {
        this.primero = primero;
    }


    public NL getUltimo() {
        return ultimo;
    }

  
    public void setUltimo(NL ultimo) {
        this.ultimo = ultimo;
    }

  
    public int getElementos() {
        return elementos;
    }

  
    public void setElementos(int elementos) {
        this.elementos = elementos;
    }

  
    public int getSuelos() {
        return suelos;
    }

  
    public void setSuelos(int suelos) {
        this.suelos = suelos;
    }

  
    public int getParedes() {
        return paredes;
    }

   
    public void setParedes(int paredes) {
        this.paredes = paredes;
    }

   
    public int getGoombas() {
        return goombas;
    }

    public void setGoombas(int goombas) {
        this.goombas = goombas;
    }

   
    public int getKoopas() {
        return koopas;
    }

   
    public void setKoopas(int koopas) {
        this.koopas = koopas;
    }

   
    public int getFichas() {
        return fichas;
    }

   
    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

   
    public int getHongos() {
        return hongos;
    }

   
    public void setHongos(int hongos) {
        this.hongos = hongos;
    }

   
    public int getPersonajes() {
        return personajes;
    }

    
    public void setPersonajes(int personajes) {
        this.personajes = personajes;
    }

    
    public int getCastillos() {
        return castillos;
    }

   
    public void setCastillos(int castillos) {
        this.castillos = castillos;
    }

  
    public boolean isPila() {
        return pila;
    }

   
    public void setPila(boolean pila) {
        this.pila = pila;
    }

   
    public boolean isCola() {
        return cola;
    }

   
    public void setCola(boolean cola) {
        this.cola = cola;
    }
    
    
}
