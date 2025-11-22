package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Ticket;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TicketPrint extends ComandTicket{
    private Ticket ticketActive;
    private ArrayList<Ticket> ticketList;
    public TicketPrint(Ticket ticketActive, ArrayList<Ticket> ticketList){
        this.ticketActive=ticketActive;
        this.ticketList=ticketList;
    }
    /**
     *  Imprime el ticket actual y guarda ticket .
     */
    public void apply(Ticket ticketActive, ArrayList<Ticket> ticketList) {
        System.out.println(ticketActive.ToString());
        ticketList.add(ticketActive);
    }
}
