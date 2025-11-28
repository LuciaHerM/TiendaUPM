package es.upm.etsisi.poo.Comands;
import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;

import java.util.ArrayList;

public class TicketList extends ComandTicket{

    private ArrayList<Cash> cashers;
    public TicketList(ArrayList<Cash> cashers) {
        this.cashers=cashers;
    }
    public void apply() {
        System.out.println("Ticket List:");
        for (Cash casher : cashers) {
            for (Ticket ticket : casher.getCashTickets()){
                System.out.println("  " + ticket.getTicketId() + " - " + ticket.getStatus());
            }
        }
        System.out.println("ticket list: ok");
    }
}
