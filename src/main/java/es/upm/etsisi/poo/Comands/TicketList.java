package es.upm.etsisi.poo.Comands;
import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;

import java.util.ArrayList;

public class TicketList extends ComandTicket{

    private ArrayList<Ticket> tickets;
    private ArrayList<Cash> cashers;
    public TicketList(ArrayList<Ticket> tickets, ArrayList<Cash> cashers) {
        this.tickets = tickets;
        this.cashers=cashers;
    }
    public void apply() {
        for (Cash casher : cashers) {
            System.out.println(casher.getCashTickets().toString());
        }
    }
}
