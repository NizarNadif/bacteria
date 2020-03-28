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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

;

/**
 * Classe d'esempio per la gara
 *
 * @author Alessandro Bugatti &lt; alessandro.bugatti@gmail.com &gt;
 */
public class Comunicatore extends batteri.Batterio implements Cloneable {

    private int spostamentoX = 1;
    private int spostamentoY = 0;
    private int versoX = (int) (Math.random() * 2) * 2 - 1;
    private int versoY = (int) (Math.random() * 2) * 2 - 1;
    private int situazione = 0;
    private static LinkedList<Integer> listaX = new LinkedList();
    private static LinkedList<Integer> listaY = new LinkedList();

    public Comunicatore(int x, int y, Color c, batteri.Food f) {
        super(x, y, c, f);
    }

    private void controllaListe() {
        if (listaX.size() > 100) {
            listaX.remove(0);
            listaY.remove(0);
        }
    }

    private boolean controlloVicini(int delta) {
        int xMigliore = x - delta - 10;
        int yMigliore = y - delta - 10;
        int indice = 0;
        boolean nonSegnalato = true;
        int sforzoMigliore = Math.abs(getX() - xMigliore) + Math.abs(getY() - yMigliore);
        for (int i = -delta; i <= delta; i += delta / 5) {
            for (int j = -delta - 1; j <= delta - 1; j += delta / 5) {
                if (ControllaCibo(x + i, y + j)) {
                    if (nonSegnalato) {
                        listaX.add(x + i);
                        listaY.add(y + j);
                        nonSegnalato = false;
                    }
                    int sforzo = Math.abs(getX() - (x + i)) + Math.abs(getY() - (y + j));
                    if (sforzo < sforzoMigliore) {
                        xMigliore = x + i;
                        yMigliore = y + j;
                        indice = listaX.size() - 1;
                        sforzoMigliore = Math.abs(getX() - xMigliore) + Math.abs(getY() - yMigliore);
                    }
                }
            }
        }
        if (xMigliore != x - delta - 10) {
            x = xMigliore;
            y = yMigliore;
            listaX.remove(indice);
            listaY.remove(indice);
            return true;
        } else {
            return false;
        }
    }

    private void spostamento() {
        if ((x + spostamentoX < 100 && versoX == -1) || (x + spostamentoX >= getFoodWitdh() - 100 && versoX == 1)) {
            versoX = -versoX;
        }

        if ((y + spostamentoY < 100 && versoY == -1) || (y + spostamentoY >= getFoodHeight() - 100 && versoY == 1)) {
            versoY = -versoY;
        }

        x += spostamentoX * versoX;
        y += spostamentoY * versoY;

        int temp = spostamentoX;
        spostamentoX = spostamentoY;
        spostamentoY = temp;

    }

    @Override
    protected void Sposta() {

        //Controlla nel 7x7 attorno a lui se c'è del cibo e tiene in memoria la prima posizione in cui l'ha trovato e l'ultima
        //Il batterio segnalerà agli altri
        switch (situazione) {
            case 0: {
                if (!controlloVicini(5)) {
                    if (!controlloVicini(100)) {
                        situazione = 1;
                        spostamento();
                    }
                }
                break;
            }
            case 1: {

                int xMigliore = x - 150;
                int yMigliore = y - 150;
                int indice = 0;
                int sforzoMigliore = Math.abs(getX() - xMigliore) + Math.abs(getY() - yMigliore);
                for (int i = 0; i < listaX.size(); i++) {
                    int xAttuale = listaX.get(i);
                    int yAttuale = listaY.get(i);
                    int sforzo = Math.abs(getX() - (xAttuale)) + Math.abs(getY() - (yAttuale));
                    if (sforzo < sforzoMigliore) {
                        xMigliore = xAttuale;
                        yMigliore = yAttuale;
                        indice = i;
                        sforzoMigliore = Math.abs(getX() - xMigliore) + Math.abs(getY() - yMigliore);
                    }
                }
                if (xMigliore != x - 150) {
                    x = xMigliore;
                    y = yMigliore;
                    listaX.remove(indice);
                    listaY.remove(indice);
                } else {
                    spostamento();
                }
                situazione = 0;
                break;
            }
            default:
                break;
        }
    }

    @Override
    public batteri.Batterio Clona() {
        try {
            return (Comunicatore) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Comunicatore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
