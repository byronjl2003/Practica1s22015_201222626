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
public class MDisp {
    
    private LNC Lcolumnas;

        public MDisp()
        {
            this.Lcolumnas = new LNC();

        }
        public Boolean Vacio()
        {
            return this.Lcolumnas.Vacio();
        }

        public void Insertar(String col,int fil,int dimension,String dato)
        {
            NC Columna = this.Lcolumnas.AgregarColumna(col);
            NF Fila = this.Lcolumnas.ListaFilas.Agregar(fil);
            NCasilla casilla = Columna.Agregar(dato,dimension,Fila);
           
        }

        public void graficarMDispersa()
        {/*
            StringBuilder[] recolector = this.Lcolumnas.ListaFilas.GraficaHorizontal();
            StringBuilder[] reco = this.Lcolumnas.GraficaVertical();
            for (int i = 0; i < recolecto r.Length; i++)
            {
                string mydocpath = Environment.GetFolderPath(Environment.SpecialFolder.Desktop);
                using (StreamWriter outfile = new StreamWriter(mydocpath + "\\GraficasArboles" + @"\Matriz_" + (i + 1) + ".txt"))
                {
                    StringBuilder gen = new StringBuilder();
                    gen.AppendLine("digraph g{");
                    gen.AppendLine("ranksep = .5; splines=ortho;");
                    gen.AppendLine(recolector[i].ToString());
                    gen.AppendLine(reco[i].ToString());
                    gen.AppendLine("}");
                    outfile.Write(gen.ToString());
                }
                

            }*/

        }

    
}
