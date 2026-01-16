package es.upm.etsisi.poo.Comands;

import java.util.ArrayList;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.TiendaUPMExcepcion;

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
    public void apply() throws TiendaUPMExcepcion {
        es.upm.etsisi.poo.Cash cajero = null;
        for (es.upm.etsisi.poo.Cash cash : cashers) {
            if (cash.getCashId().equals(id)) {
                cajero = cash;
            }
        }
        if (cajero != null) {
            ArrayList<Ticket> ticketList = cajero.getCashTickets();
            if(ticketList.isEmpty()){
                System.out.println("Tickets: ");
            } else {
                System.out.println("Tickets: ");
                for (int i = 0; i < ticketList.size(); i++) {
                    Ticket ticket = ticketList.get(i);
                    System.out.println("  " + ticket.getTicketId() + " ->" + ticket.getStatus());
                }
            }
            System.out.println("cash tickets: ok");
        } else {
            throw new TiendaUPMExcepcion("The casher was not found", "ERR_CASHID");
        }
    }
}
