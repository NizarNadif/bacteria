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
public class TontinoNadif extends batteri.Batterio implements Cloneable {
    
    private int mosseCompiute; //ogni 25 mosse facciamo un'ampia scansione
    private int energia = 400; //energia stimata di avere all'inizio (minimo = 200, massimo = 800, media = 500)
    
    private int spostamentoX = 1;
    private int spostamentoY = 0;
    private int versoX = (int)(Math.random()*2) * 2 - 1;
    private int versoY = (int)(Math.random()*2) * 2 - 1;

    public TontinoNadif(int x, int y, Color c, batteri.Food f) {
        super(x, y, c, f);
        this.mosseCompiute = 0;
    }
    
    /**
     * Scannerizza l'area circostante in cerca di cibo e si sposta
     * alla fonte di cibo che apporta il minor sforzo possibile
     * @param delta raggio della scansione
     */
    private void controlloVicini(int delta) {
        
        int xMigliore = x - delta - 10;
        int yMigliore = y - delta - 10;
        
        int sforzoMigliore = Math.abs(getX() - xMigliore) + Math.abs(getY() - yMigliore);
        int sforzo;
        
        for (int i = -delta; i <= delta; i += delta/5) {
            for (int j = -delta; j <= delta; j += delta/5) {
                if (ControllaCibo(x + i, y + j)) {
                    sforzo = Math.abs(getX() - (x + i)) + Math.abs(getY() - (y + j));
                    if (sforzo < sforzoMigliore) {
                        xMigliore = x + i;
                        yMigliore = y + j;
                        sforzoMigliore = Math.abs(getX() - xMigliore) + Math.abs(getY() - yMigliore);
                    }
                }
            }
        }
        if (xMigliore != x - delta - 10 && sforzoMigliore < energia){
            x = xMigliore;
            y = yMigliore;
            energia +=  100 - sforzoMigliore;
        }
        else {
           if ( (x + spostamentoX < 75 && versoX == -1) || (x + spostamentoX >= getFoodWitdh() - 75 && versoX == 1) )
               versoX = -versoX;
           
           if ( (y + spostamentoY < 75 && versoY == -1) || (y + spostamentoY >= getFoodHeight() - 75 && versoY == 1) )
               versoY = -versoY;
           
            x += spostamentoX * versoX;
            y += spostamentoY * versoY;
            
            int temp = spostamentoX;
            spostamentoX = spostamentoY;
            spostamentoY = temp;
            
            /* eH mA pUoI fArE lO sCaMbIo SeNzA uSaRe NeSsUnA vArIaBiLe
            *spostamentoX += spostamentoY;
            spostamentoY = spostamentoX - spostamentoY;
            spostamentoX -= spostamentoY;
            */
            energia--;
        }
        mosseCompiute = (mosseCompiute + 1)%25;
    }

    @Override
    protected void Sposta() {
        int delta = 10;
        //ogni 25 tic il batterio compie un'ampia ispezione del campo (200 x 200)
        if (mosseCompiute == 0)
            delta = 100;
        
        controlloVicini(delta);
    }

    @Override
    public batteri.Batterio Clona() {
        try {
            return (TontinoNadif) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(TontinoNadif.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
