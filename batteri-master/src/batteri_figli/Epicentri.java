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

import batteri.Food;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe d'esempio per la gara
 *
 * @author Alessandro Bugatti &lt; alessandro.bugatti@gmail.com &gt;
 */
public class Epicentri extends batteri.Batterio implements Cloneable {

    /**
     * CARATTERISTICHE:
     * - Controllo ad area con delta 5, se non va a a buon fine spostamento orizzontale e situazione successiva
     * - Oppure controllo ad area con delta 100, se non va a buon fine spostamento orizzontale e situazione precedente
     * - Tutto viene svolto in 2 turni differenti
     **/
    private final static int ZONES_ROW = 9;
    private final static int ZONES_COL = 11;
    private static int[] zonesX = new int[ZONES_COL];
    private static int[] zonesY = new int[ZONES_ROW];

    private static boolean state = false;
    private int[] temp;
    private boolean isEqual = false;

    public Epicentri(int x, int y, Color c, Food f) {
        super( x, y, c, f );
        if ( !this.state ) {

            this.zonesX[0] = 47;
            this.zonesY[0] = 39;

            for (int i = 1; i < this.ZONES_ROW; i++) {
                this.zonesY[i] = this.zonesY[i - 1] + 79;
            }
            for (int j = 1; j < this.ZONES_COL; j++) {

                this.zonesX[j] = this.zonesX[j - 1] + 94;
            }
            this.state = true;
        }
        this.temp = findStrategicPosition();
    }

    private int[] findStrategicPosition() {
        int xMigliore = 3000;
        int yMigliore = 1500;
        int sforzoMigliore = Math.abs( getX() - xMigliore ) + Math.abs( getY() - yMigliore );
        for (int i = 0; i < this.ZONES_ROW; i++) {
            for (int j = 0; j < this.ZONES_COL; j++) {
                int sforzo = Math.abs( getX() - this.zonesX[j] ) + Math.abs( getY() - this.zonesY[i] );
                if ( sforzo < sforzoMigliore ) {
                    xMigliore = this.zonesX[j];
                    yMigliore = this.zonesY[i];
                    sforzoMigliore = sforzo;
                }
            }
        }

        if ( xMigliore != 3000 ) {
            //System.out.println("xMig: " + xMigliore + " yMig: " + yMigliore);
            return new int[]{xMigliore, yMigliore};
        }
        return null;
    }

    private boolean controlloLontani(int delta,int div) {
        int xMigliore = x - delta - 10;
        int yMigliore = y - delta - 10;
        int sforzoMigliore = Math.abs( getX() - xMigliore ) + Math.abs( getY() - yMigliore );
        for (int i = -delta; i <= delta; i += (int)(delta / div)) {
            for (int j = -delta - 1; j <= delta - 1; j += (int)(delta / div)) {
                if ( ControllaCibo( x + i, y + j ) ) {
                    int sforzo = Math.abs( getX() - (x + i) ) + Math.abs( getY() - (y + j) );
                    if ( sforzo < sforzoMigliore ) {
                        xMigliore = x + i;
                        yMigliore = y + j;
                        sforzoMigliore = Math.abs( getX() - xMigliore ) + Math.abs( getY() - yMigliore );
                    }
                }
            }
        }
        if ( xMigliore != x - delta - 10 ) {
            x = xMigliore;
            y = yMigliore;
            return true;
        } else {
            return false;
        }
    }

    private boolean controlloVicini(int delta,int div) {
        int xMigliore = x - delta - 10;
        int yMigliore = y - delta - 10;
        int sforzoMigliore = Math.abs( getX() - xMigliore ) + Math.abs( getY() - yMigliore );
        for (int i = -delta; i <= delta; i +=(int)(delta / div)) {
            for (int j = -delta - 1; j <= delta - 1; j += (int)(delta / div)) {
                if ( ControllaCibo( x + i, y + j ) ) {
                    int sforzo = Math.abs( getX() - (x + i) ) + Math.abs( getY() - (y + j) );
                    if ( sforzo < sforzoMigliore ) {
                        xMigliore = x + i;
                        yMigliore = y + j;
                        sforzoMigliore = Math.abs( getX() - xMigliore ) + Math.abs( getY() - yMigliore );
                    }
                }
            }
        }
        if ( xMigliore != x - delta - 10 ) {
            x = xMigliore;
            y = yMigliore;
            return true;
        } else {
            return false;
        }
    }

    private int spostamentoX = 1;
    private int spostamentoY = 0;
    private int versoX = (int) (Math.random() * 2) * 2 - 1;
    private int versoY = (int) (Math.random() * 2) * 2 - 1;

    private void spostamento() {
        if((x + spostamentoX < temp[0]-47 && versoX == -1) || (x + spostamentoX >= temp[0]+47  && versoX == 1)) {
            versoX = -versoX;
        }

        if ((y + spostamentoY < temp[1]-39 && versoY == -1) || (y + spostamentoY >= temp[1]+39  && versoY == 1)) {
            versoY = -versoY;
        }
        /*if ((x + spostamentoX < 75 && versoX == -1) || (x + spostamentoX >= getFoodWitdh()-75  && versoX == 1)) {
            versoX = -versoX;
        }

        if ((y + spostamentoY < 75 && versoY == -1) || (y + spostamentoY >= getFoodHeight()-75  && versoY == 1)) {
            versoY = -versoY;
        }*/

        x += spostamentoX * versoX;
        y += spostamentoY * versoY;

        int temp = spostamentoX;
        spostamentoX = spostamentoY;
        spostamentoY = temp;
    }

    private void place(int posX, int posY) {
        if ( getX() > posX ) {
            x--;
        }
        else if ( getX() < posX ) {
            x++;
        }
        else if ( getY() > posY ) {
        y--;
        }
        else if ( getY() < posY ) {
            y++;
        }
        if ( getX() == posX && getY() == posY ) {
            isEqual = true;
            toMove = true;
        }
    }

    private boolean toMove = false;

    @Override
    protected void Sposta() {
        if ( !isEqual )
            place( temp[0], temp[1] );
        else {
            if ( !controlloVicini( 5,5 ) ) {
                if ( !controlloLontani( 100,5 ) ) {
                    if( toMove && isEqual )
                        spostamento();
                    else{
                        isEqual = false;
                    }
                }else {
                    findStrategicPosition();
                    toMove = false;
                }
            }
        }
    }
    //System.out.println("x: " + getX() + " y: " + getY());

    @Override
    public batteri.Batterio Clona() {
        try {
            return (Epicentri) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Epicentri.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return null;
    }

}
