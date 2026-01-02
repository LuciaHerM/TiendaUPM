package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;

import java.util.ArrayList;
import java.util.List;
    /**
    * Busca el ID dentro del arrayList de elementos y si lo encuentra elimina el cajero , junto con sus tickets asociados , en cambio
    * si no lo encuentra dispara un mensaje de error de que el ID no es correcto.
    */
public class ComandCashRemove extends ComandCash {
    private String id;
    private ArrayList<Cash> cashers;

    public ComandCashRemove(String id, ArrayList<Cash> cashers) {
        this.id = id;
        this.cashers = cashers;
    }
    public void apply(){
        boolean encontrado = false;
        int i =0;
        while(i < cashers.size()&&!encontrado){
            if (cashers.get(i).getId().equals(id)) {
                encontrado = true;
            }
            else {
                i++;
            }
        }
        if(encontrado){
            int longitudTicketsDeEsteCash = cashers.get(i).getCashTickets().size();
            for (int j = 0; j < longitudTicketsDeEsteCash; j++) {
                cashers.get(i).getCashTickets().clear();
            }
            cashers.remove(i);
            System.out.println("cash remove: ok");
        }
        else {
            System.out.println("That id can't be find");
        }

    }
}
