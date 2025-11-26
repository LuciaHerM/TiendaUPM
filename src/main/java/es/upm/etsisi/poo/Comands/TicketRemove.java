package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TicketStatus;

import java.util.ArrayList;

public class TicketRemove extends ComandTicket{
    private String ticketId;
    private String cashId;
    private String prodId;
    private Catalog catalog;
    private Ticket ticketActive;
    public TicketRemove(String ticketId, String cashId, String prodId, Catalog catalog, ArrayList<Ticket> ticketList, ArrayList<Cash> cashList, Ticket ticketActive){
        this.catalog=catalog;
        this.ticketId=ticketId;
        this.ticketId=cashId;
        this.prodId=prodId;
        this.ticketActive=ticketActive;
    }
    /**
     * Elimina un producto del ticket.
     */
    public void apply() {
        if(ticketActive.getStatus()!= TicketStatus.CERRADO) {
            int i = 0;
            boolean encontrado = false;
            while (!encontrado && i < catalog.length()) {
                if (catalog.find(i) != null && catalog.find(i).getID().equals(prodId)) {
                    encontrado = true;
                } else {
                    i++;
                }
            }
            if (encontrado) {
                ticketActive.RemoveProduct(catalog.find(i));
                System.out.println(ticketActive.ToString());
                System.out.println("ticket remove: ok");
            } else {
                System.err.println("This product can't be found");
            }
        } else {
            System.err.println("The ticket was closed");
        }

    }
}
