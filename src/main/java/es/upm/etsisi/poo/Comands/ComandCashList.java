package es.upm.etsisi.poo.Comands;

import java.util.ArrayList;
import java.util.List;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;

/**
    * Realiza un bucle para ir mostrando en pantalla los datos de cada cajero guardado en el arrayList
    */
public class ComandCashList extends ComandCash {
    es.upm.etsisi.poo.Cash casher;
    private ArrayList<Cash> cashers;

    public ComandCashList(ArrayList<Cash> cashers) {
        this.cashers = cashers;
    }
    /**
     * Realiza un bucle para ir mostrando en pantalla los datos de cada cajero guardado en el arrayList
     */
    public void apply() {
        cashers.sort((c1,c2)->c1.getName().compareTo(c2.getName()));
        System.out.println("Cash:");
        for(int i = 0 ; i < cashers.size();i++){
            System.out.println("  " + cashers.get(i).toString());
        }
        if(cashers.isEmpty()){
            System.out.println("The cash list is empty");
        }
        System.out.println("cash list: ok");
    }
}
