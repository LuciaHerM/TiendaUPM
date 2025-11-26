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
    private ArrayList<Cash> cashers;
    public TicketRemove(String ticketId, String cashId, String prodId, Catalog catalog,ArrayList<Cash> cashList){
        this.catalog=catalog;
        this.ticketId=ticketId;
        this.ticketId=cashId;
        this.prodId=prodId;
        this.cashers=cashList;
    }
    /**
     * Elimina un producto del ticket.
     */
    public void apply() {
        Ticket ticketActive = encontrarTicket();
        if(ticketActive!= null && ticketActive.getStatus()!= TicketStatus.CERRADO) {
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
                System.out.println(ticketActive.toString());
                System.out.println("ticket remove: ok");
            } else {
                System.err.println("This product can't be found");
            }
        } else {
            System.err.println("The ticket was closed");
        }

    }
    private Ticket encontrarTicket() {
        Cash casher = null;
        for (int i = 0; i < cashers.size(); i++) {
            if ( cashers.get(i).getId().equals(cashId)) {
                casher = cashers.get(i);
            }
        }

        Ticket ticket = null;
        boolean encontrado=false;
        int i=0;
        if(casher!=null) {
            ArrayList<Ticket> tickets = casher.getCashTickets();
            while (!encontrado && i < tickets.size()) {
                if (tickets.get(i).getTicketId().equals(ticketId)) {
                    ticket = tickets.get(i);
                } else {
                    i++;
                }
            }
        }
        if(encontrado) {
            return ticket;
        }else {
            System.out.println("No se encontro ticket con ese id");
            return null;
        }
    }
}
