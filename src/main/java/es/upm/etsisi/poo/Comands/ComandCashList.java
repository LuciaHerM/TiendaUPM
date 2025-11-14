package es.upm.etsisi.poo.Comands;

import java.util.ArrayList;
import es.upm.etsisi.poo.Cash;

    /**
    * Realiza un bucle para ir mostrando en pantalla los datos de cada cajero guardado en el arrayList
    */
public class ComandCashList extends ComandCash {
    es.upm.etsisi.poo.Cash casher;
    public void apply(ArrayList<Cash> cashers) {
        cashers.sort((c1,c2)->c1.getName().compareTo(c2.getName()));
        for(int i = 0 ; i < cashers.size();i++){
            System.out.println(cashers.get(i).toString());
        }
    }
}
