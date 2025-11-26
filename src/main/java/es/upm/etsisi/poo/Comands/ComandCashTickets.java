package es.upm.etsisi.poo.Comands;

import java.util.ArrayList;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;

public class ComandCashTickets extends ComandCash {

    private String id;
    private ArrayList<Cash> cashers;

    public ComandCashTickets(String id, ArrayList<Cash> cashers) {
        this.id = id;
        this.cashers = cashers;
    }

    /**
     * Te busca el cajero y posteriormente te imprime los tickets del cajero.
     */
    public void apply() {
        es.upm.etsisi.poo.Cash cajero = null;
        for (es.upm.etsisi.poo.Cash cash : cashers) {
            if (cash.getId().equals(id)) {
                cajero = cash;
            }
        }
        if (cajero != null) {
            ArrayList<Ticket> ticketList = cajero.getCashTickets();
            for (int i = 0; i < ticketList.size(); i++) {
                System.out.println(ticketList.get(i).toString());
            }
            if(ticketList.isEmpty()){
                System.out.println("The cash don't have tickets created");
            }
            System.out.println("cash tickets: ok");
        } else {
            System.out.println("The casher was not found");
        }
    }
}
