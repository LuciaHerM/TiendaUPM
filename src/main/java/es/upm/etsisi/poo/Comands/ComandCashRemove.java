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
    private ArrayList<Ticket> ticketList;

    public ComandCashRemove(String id, ArrayList<Cash> cashers, ArrayList<Ticket> ticketList) {
        this.id = id;
        this.cashers = cashers;
        this.ticketList = ticketList;
    }

        es.upm.etsisi.poo.Cash casher;
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
            List<Ticket> ticketsCajero = cashers.get(i).getCashTickets();
            for(int j = 0 ; j<ticketsCajero.size();j++ ){
                ticketList.remove(ticketsCajero.get(i));
            }
            cashers.remove(i);
        }
        else {
            System.err.println("El id introducido no se encuentra en la base de datos del sistema");
        }

    }
}
