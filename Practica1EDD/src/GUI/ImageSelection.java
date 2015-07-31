/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ListaPilaCola.Lista;
import ListaPilaCola.NL;
import MDisp.NM;
import Objetos.Objeto;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

/**
 *
 * @author byron
 */
class ImageSelection extends TransferHandler implements Transferable 
{

  private static final DataFlavor flavors[] = { DataFlavor.imageFlavor };

  private Image image;
  Lista lista;
  Maker maker;
  
  public ImageSelection(Lista l,Maker mak)
  {
      this.lista = l;
      this.maker = mak;
  }

    @Override
  public int getSourceActions(JComponent c) {
    return TransferHandler.COPY;
  }

    @Override
  public boolean canImport(JComponent comp, DataFlavor flavor[]) {
    if (!(comp instanceof JLabel) && !(comp instanceof AbstractButton) && !(comp instanceof NM)) {
      return false;
    }
    for (int i = 0, n = flavor.length; i < n; i++) {
      for (int j = 0, m = flavors.length; j < m; j++) {
        if (flavor[i].equals(flavors[j])) {
          return true;
        }
      }
    }
    return false;
  }

    @Override
  public Transferable createTransferable(JComponent comp) {
    // Clear
    image = null;

    if (comp instanceof JLabel)
    {
      JLabel label = (JLabel) comp;
      Icon icon = label.getIcon();
      if (icon instanceof ImageIcon) {
        image = ((ImageIcon) icon).getImage();
        return this;
      }
    }
    else if (comp instanceof AbstractButton) 
    {
      AbstractButton button = (AbstractButton) comp;
      Icon icon = button.getIcon();
      if (icon instanceof ImageIcon) {
        image = ((ImageIcon) icon).getImage();
        return this;
      }
    }
    else if (comp instanceof NM) 
    {
      NM panel = (NM) comp;
      Image icon = panel.Dato.getImage();
      if (icon instanceof Image) {
        image = icon;
        return this;
      }
    }
    return null;
  }

    @Override
  public boolean importData(JComponent comp, Transferable t) {
    if (comp instanceof JLabel)
    {
      JLabel label = (JLabel) comp;
      if (t.isDataFlavorSupported(flavors[0])) {
        try {
          image = (Image) t.getTransferData(flavors[0]);
          ImageIcon icon = new ImageIcon(image);
          label.setIcon(icon);
          return true;
        } catch (UnsupportedFlavorException ignored) {
        } catch (IOException ignored) {
        }
      }
    }
    else if (comp instanceof AbstractButton)
    {
      AbstractButton button = (AbstractButton) comp;
      if (t.isDataFlavorSupported(flavors[0])) {
        try {
          image = (Image) t.getTransferData(flavors[0]);
          ImageIcon icon = new ImageIcon(image);
          button.setIcon(icon);
          return true;
        } catch (UnsupportedFlavorException ignored) {
        } catch (IOException ignored) {
        }
      }
    }
    else if (comp instanceof NM)
    {
      NM panel = (NM) comp;
      if (t.isDataFlavorSupported(flavors[0])) {
        try {
          image = (Image) t.getTransferData(flavors[0]);
          NL aux = lista.Eliminar();
          if(aux!=null)
              System.out.println("NL NO NULO "+aux.getObjeto().nombre);
          else
              System.out.println("NL ES NULO");
          panel.Dato.setImage(image);
//          System.out.println(lista.Siguiente().nombre);
          panel.repaint();
          maker.refreshActual();
          
          return true;
        } catch (UnsupportedFlavorException ignored) {
            ignored.printStackTrace();
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
      }
    }
    return false;
  }

  // Transferable
    @Override
  public Object getTransferData(DataFlavor flavor) {
    if (isDataFlavorSupported(flavor)) {
      return image;
    }
    return null;
  }

    @Override
  public DataFlavor[] getTransferDataFlavors() {
    return flavors;
  }

    @Override
  public boolean isDataFlavorSupported(DataFlavor flavor) {
    return flavors[0].equals(flavor);
  }
}