package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.TicketStatus;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TicketPrint extends ComandTicket{
    private Ticket ticketActive;
    private ArrayList<Ticket> ticketList;
    public TicketPrint(String ticketId, String cashId){
        this.ticketActive=ticketActive;
        this.ticketList=ticketList;
    }
    /**
     *  Imprime el ticket actual y guarda ticket .
     */
    public void apply() {
        System.out.println(ticketActive.ToString());
        ticketActive.setStatus(TicketStatus.CERRADO);
        ticketList.add(ticketActive);
    }
}
