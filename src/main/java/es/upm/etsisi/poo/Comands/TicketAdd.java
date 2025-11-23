package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TicketStatus;

import java.util.ArrayList;

public class TicketAdd extends ComandTicket{

    private String prodId;
    private String quantity;
    private Catalog catalog;
    private Ticket ticketActive;
    private String ticketId;
    private String[] personalizaciones ;
    private Cash casher;
    public TicketAdd(String ticketId,String cashId , String prodId, String quantity,String personalizacion , Catalog catalog,ArrayList<Ticket> ticketsList,ArrayList<Cash> cashers){
        this.prodId=prodId;
        this.quantity=quantity;
        this.catalog=catalog;
        this.ticketActive = seleccinarTicket(ticketsList, ticketId);
        this.personalizaciones = personalizacion.split("--p");
        this.casher = seleccinarCash(cashers,cashId) ;
    }
    public TicketAdd(String ticketId,String cashId,String prodId, String quantity, Catalog catalog,ArrayList<Ticket> ticketsList,ArrayList<Cash> cashers){
        this.prodId=prodId;
        this.quantity=quantity;
        this.catalog=catalog;
        this.ticketActive = seleccinarTicket(ticketsList, ticketId);
        this.personalizaciones = null;
        this.casher = seleccinarCash(cashers,cashId) ;
    }
    /**
     *  Añade una cantidad específica de un producto al ticket.
     */
    public void apply() {
        if(ticketActive!=null) {
            if (ticketActive.getStatus() != TicketStatus.CERRADO) {
                int cont = 0;
                boolean encontrado = false;
                while (cont < catalog.length() && !encontrado) {
                    if (catalog.find(cont) != null && catalog.find(cont).getID().equals(prodId)) {
                        encontrado = true;
                    } else {
                        cont++;
                    }
                }
                if (encontrado) {
                    if (ticketActive.getStatus() != TicketStatus.ACTIVO) {
                        ticketActive.setStatus(TicketStatus.ACTIVO);
                    }
                    for (int i = 0; i < Integer.parseInt(quantity); i++) {
                        ticketActive.AddProduct(catalog.find(cont));
                    }
                    System.out.println(ticketActive.toString());
                    System.out.println("ticket add: ok");
                } else {
                    System.err.println("The product was not found");
                }
            } else {
                System.err.println("The ticket was closed");
            }
        }
        else {
            System.err.println("No hay un ticket con el id introducido .");
        }
    }
    public Ticket seleccinarTicket(ArrayList<Ticket> ticketsList ,String ticketId ){
        Ticket ticketActual = null;
        for (int i = 0; i < ticketsList.size(); i++) {
            if ( ticketsList.get(i).getTicketId().equals(ticketId)) {
                ticketActual = ticketsList.get(i);
            }
        }
        return ticketActual;
    }
    public Cash seleccinarCash(ArrayList<Cash> cashers ,String cashId ){
        Cash casherActual = null;
        for (int i = 0; i < cashers.size(); i++) {
            if ( cashers.get(i).getId().equals(cashId)) {
                casherActual = cashers.get(i);
            }
        }
        return casherActual;
    }


}
