package es.upm.etsisi.poo.Comands;

import java.util.ArrayList;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;

public class ComandCashTickets extends ComandCash {

    private String id;
    private ArrayList<Cash> cashers;
    private ArrayList<Ticket> ticketList;

    public ComandCashTickets(String id, ArrayList<Cash> cashers, ArrayList<Ticket> ticketList) {
        this.id = id;
        this.cashers = cashers;
        this.ticketList = ticketList;
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
            for (int i = 0; i < ticketList.size(); i++) {
                System.out.println(cajero.getCashTickets().get(i).toString());
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
