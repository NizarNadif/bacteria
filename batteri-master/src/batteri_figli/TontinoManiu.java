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
 * @author Alessandro Bugatti &lt; alessandro.bugatti@gmail.com &gt;
 */

public class TontinoManiu extends batteri.Batterio implements Cloneable{
    
    private int mosseCompiute;
    private int spostamentoX = 1;
    private int spostamentoY = 0;
    private int versoX = 1;
    private int versoY = 1;
    private int caso = 0;

    public TontinoManiu(int x, int y, Color c, batteri.Food f){
        super(x,y,c,f);
        this.mosseCompiute = 0;
    }

    private boolean controlloVicini(int delta, int div) {
        int xMigliore = x - delta - 10;
        int yMigliore = y - delta - 10;
        int sforzoMigliore = Math.abs(getX() - xMigliore) + Math.abs(getY() - yMigliore);
        
        for (int i = -delta; i <= delta; i += delta/div) {
            for (int j = -delta; j <= delta; j += delta/div) {
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
        if (xMigliore != x - delta - 10 && Math.abs(xMigliore - x) + Math.abs(yMigliore - y) < 250 ){
            x = xMigliore;
            y = yMigliore;
            mosseCompiute++;
            return true;
        }
        else if (Math.abs(xMigliore - x) + Math.abs(yMigliore - y) >= 250 )
        {
            spostamentoNullo();
            return false;
        }
        else {
            spostamentoNullo();
            return false;
        }
    }
    public void spostamentoNullo()
    {
           if ( (x + spostamentoX < 75 && versoX == -1) || (x + spostamentoX >= getFoodWitdh() - 75 && versoX == 1) )
               versoX = -versoX;
           
           if ( (y + spostamentoY < 75 && versoY == -1) || (y + spostamentoY >= getFoodHeight() - 75 && versoY == 1) )
               versoY = -versoY;
           
            x += spostamentoX * versoX;
            y += spostamentoY * versoY;
            
            int temp = spostamentoX;
            spostamentoX = spostamentoY;
            spostamentoY = temp;
            mosseCompiute++;
    }

    @Override
    protected void Sposta() {
        switch(caso){
            case 0: if(!controlloVicini(10, 1)) caso++; break;
            case 1: if(!controlloVicini(100, 10)) caso++; else caso = 0; break;
            case 2: spostamentoNullo(); caso = 0;
        }            
    }
    
    @Override
    public batteri.Batterio Clona(){
       try { 
            return (TontinoManiu)this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Tontino.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
}
