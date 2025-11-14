package es.upm.etsisi.poo.Comands;

import java.util.ArrayList;
import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;


public class ComandCashAdd extends ComandCash {
    es.upm.etsisi.poo.Cash casher;
    /**
     * Comprueba que no esiste un cajero con el mismo nombre y si no esiste crea
     * un cajero y o añade al arrayList de cajeros
     */
    public void apply(String name, String email, String id, ArrayList<Cash> cashers){
        boolean encontrado = false;
        int i = 0 ;
        while (!encontrado&&i<cashers.size()) {
            if (cashers.get(i).getId().equals(id)) {
                encontrado = true;
            }
            i++;
        }
        if(!encontrado) {
            Cash cash = new Cash(id, name, email);
            cashers.add(cash);
        }else {
            System.err.println("Ya existe un cajero con el mismo Id en la base de datos");
        }
    }
    /**
     * Busca el mayor id para crear un nuevo el cual será mayorId + 1 el cual sabemos
     * que no va a exitir
     */
    public void apply(String name, String email, ArrayList<Cash> cashers){
        int nuevoId = 0 ;
        int maximo = 0;
        for (int i = 0 ; i < cashers.size() ; i++){
            if ( maximo < Integer.parseInt(cashers.get(i).getId())){
                maximo = Integer.parseInt(cashers.get(i).getId());
            }
        }
        Cash cash = new Cash(String.valueOf(maximo+1),name,email);
        cashers.add(cash);
    }
}
