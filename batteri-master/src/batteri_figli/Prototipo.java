package batteri_figli;

/*
  Copyright (C) 2013 Alessandro Bugatti (alessandro.bugatti@istruzione.it)
  This program is free software; you can redistribute it and/or
  modify it under the terms of the GNU General Public License
  as published by the Free Software Foundation; either version 2
  of the License, or (at your option) any later version.
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe d'esempio per la gara
 *
 * @author Alessandro Bugatti &lt; alessandro.bugatti@gmail.com &gt;
 */
public class Prototipo
        extends batteri.Batterio implements Cloneable {

    private int mosseCompiute;
    private int spostamentoNullo = 1;

    public Prototipo
        (int x, int y, Color c, batteri.Food f) {
        super(x, y, c, f);
        this.mosseCompiute = 0;
    }

    private void controlloVicini(int delta) {
        int xMigliore = x - delta - 10;
        int yMigliore = y - delta - 10;
        int sforzoMigliore = Math.abs(getX() - xMigliore) + Math.abs(getY() - yMigliore);
        for (int i = -delta; i <= delta; i += delta/5) {
            for (int j = -delta; j <= delta; j += delta/5) {
                if (ControllaCibo(x + i, y + j)) {
                    int sforzo = Math.abs(getX() - (x + i)) + Math.abs(getY() - (y + j));
                    if (sforzo < sforzoMigliore) {
                        xMigliore = x + i;
                        yMigliore = y + j;
                        sforzoMigliore = Math.abs(getX() - xMigliore) + Math.abs(getY() - yMigliore);
                    }
                }
            }
        }
        if (xMigliore != x - delta - 10){
            x = xMigliore;
            y = yMigliore;
        }
        else {
            x += spostamentoNullo;
            spostamentoNullo*=-1;
        }
        mosseCompiute++;
        mosseCompiute %= 25;
    }

    @Override
    protected void Sposta() {

        //Controlla nel 7x7 attorno a lui se c'è del cibo e tiene in memoria la prima posizione in cui l'ha trovato e l'ultima
        //Il batterio segnalerà agli altri 
        if (mosseCompiute == 0) {
            controlloVicini(100);
        } else {
            controlloVicini(5);
        }
    }

    @Override
    public batteri.Batterio Clona() {
        try {
            return (Prototipo
                    ) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Prototipo
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}