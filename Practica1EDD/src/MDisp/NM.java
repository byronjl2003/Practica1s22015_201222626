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
public class NM {
    
    public NM Next, Back;
    public String Dato;
    public int Dimension;
    public NM(String dato,int dimension)
    {
        this.Dato = dato;
        this.Next = this.Back = null;
        this.Dimension = dimension;

    }

public String ToString()
{
    if (Dimension == 1)
    {
        return "SATELITE" + Dato;
    }
    else if(Dimension==2)
    {
        return "AVION" + Dato;
    }
    else if (Dimension == 3)
    {
        return "BARCO" + Dato;
    }
    else
    {
        return "SUBMARINO" + Dato;
    }
}

    
}
