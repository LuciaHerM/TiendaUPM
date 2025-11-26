package es.upm.etsisi.poo.Comands;
import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;

import java.util.ArrayList;

public class TicketList extends ComandTicket{

    private ArrayList<Ticket> tickets;
    public TicketList(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
    private ArrayList<Cash> cashers;
    public void apply() {
        for (Cash casher : cashers) {
            System.out.println(casher.getCashTickets().toString());
        }
    }
}
