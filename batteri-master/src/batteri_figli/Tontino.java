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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe d'esempio per la gara
 *
 * @author Alessandro Bugatti &lt; alessandro.bugatti@gmail.com &gt;
 */
public class Tontino extends batteri.Batterio implements Cloneable {

    /**CARATTERISTICHE:
     * - Controllo ad area con delta 5, se non va a a buon fine spostamento orizzontale e situazione successiva
     * - Oppure controllo ad area con delta 100, se non va a buon fine spostamento orizzontale e situazione precedente
     * - Tutto viene svolto in 2 turni differenti
    **/
    
    private int spostamentoNullo;
    private int situazione = 0;

    public Tontino(int x, int y, Color c, batteri.Food f) {
        super(x, y, c, f);
        this.spostamentoNullo = (int)(Math.random()*2) * 2 - 1;
    }

    private boolean controlloVicini(int delta) {
        int xMigliore = x - delta - 10;
        int yMigliore = y - delta - 10;
        int sforzoMigliore = Math.abs(getX() - xMigliore) + Math.abs(getY() - yMigliore);
        for (int i = -delta; i <= delta; i += delta / 5) {
            for (int j = -delta - 1; j <= delta - 1; j += delta / 5) {
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
        if (xMigliore != x - delta - 10) {
            x = xMigliore;
            y = yMigliore;
            return true;
        } else 
            return false;
    }

    private void spostamento(){
        if (x + spostamentoNullo >= getFoodWitdh() || x + spostamentoNullo < 0)
                spostamentoNullo*=-1;
            x += spostamentoNullo;
    }
    
    
    @Override
    protected void Sposta() {
        if (situazione == 0) {
            boolean risultato = controlloVicini(5);
            if (!risultato) {
                situazione = 1;
                spostamento();
            }
        }
        else if (situazione == 1) {
            boolean risultato = controlloVicini(100);
            if (risultato)
                situazione = 0;
            else 
                spostamento();
        }
    }

    @Override
    public batteri.Batterio Clona() {
        try {
            return (Tontino) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger( Tontino.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
