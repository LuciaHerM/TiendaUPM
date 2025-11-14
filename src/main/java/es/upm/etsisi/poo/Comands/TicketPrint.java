package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Ticket;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TicketPrint extends ComandTicket{
    /**
     *  Imprime el ticket actual y guarda ticket .
     */
    public void apply(Ticket ticketActive, ArrayList<Ticket> ticketList) {
        System.out.println(ticketActive.ToString());
        ticketList.add(ticketActive);
        ticketActive.getCajero().ticketAddCash(ticketActive);
        ticketActive.getCliente().ticketAddClients(ticketActive);
    }
}
