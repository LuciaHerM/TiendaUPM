package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Ticket;

public class TicketPrint extends ComandTicket{
    /**
     *  Imprime el ticket actual y guarda ticket .
     */
    @Override
    public void apply() {
        System.out.println(ticketActive.ToString());
        ticketList.add(ticketActive);
        ticketActive.getCajero().ticketAddCash(ticketActive);
        ticketActive.getCliente().ticketAddClients(ticketActive);
    }
}
