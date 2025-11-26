package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Product;

import java.util.ArrayList;

public class TicketAdd extends ComandTicket{

    private String prodId;
    private String quantity;
    private Catalog catalog;
    private String ticketId;
    private String[] personalizaciones ;
    private Cash casher;


    public TicketAdd(String ticketId,String cashId , String prodId, String quantity, String[] personalizacion , Catalog catalog,ArrayList<Cash> cashers){
        this.ticketId=ticketId;
        this.prodId=prodId;
        this.quantity=quantity;
        this.catalog=catalog;
        this.casher = seleccinarCash(cashers,cashId) ;
        this.personalizaciones = personalizacion;
    }
    public TicketAdd(String ticketId,String cashId,String prodId, String quantity, Catalog catalog,ArrayList<Cash> cashers){
        this.ticketId=ticketId;
        this.prodId=prodId;
        this.quantity=quantity;
        this.catalog=catalog;
        this.casher = seleccinarCash(cashers,cashId) ;
        this.personalizaciones = null;

    }
    /**
     *  Añade una cantidad específica de un producto al ticket.
     */
    public void apply() {
        Ticket ticketActual = encontrarTicket();
        if(ticketActual !=null) {
            if (ticketActual.getStatus() != TicketStatus.CERRADO) {
                int cont = 0;
                boolean encontrado = false;
                while (cont < catalog.length() && !encontrado) {
                    if (catalog.find(cont) != null && catalog.find(cont).getID().equals(prodId)) {
                        encontrado = true;
                    } else {
                        cont++;
                    }
                }
                Product p = catalog.find(cont);
                if(encontrado&&(p instanceof Events)&&ticketActual.reunionYaIntroducida(p)){
                    encontrado=false;
                    System.err.println("no puedes introducir dos venetos iguales en el mismo ticket");
                }
                if (encontrado) {
                    if (ticketActual.getStatus() != TicketStatus.ACTIVO) {
                        ticketActual.setStatus(TicketStatus.ACTIVO);
                    }
                    for (int i = 0; i < Integer.parseInt(quantity); i++) {
                        ticketActual.AddProduct(p);
                    }
                    System.out.println(ticketActual.toString());
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

    private Ticket encontrarTicket() {
        ArrayList<Ticket> tickets = casher.getCashTickets();
        Ticket ticket = null;
        boolean encontrado=false;
        int i=0;
        while (!encontrado && i<tickets.size()){
            if(tickets.get(i).getTicketId().equals(ticketId)){
                ticket=tickets.get(i);
            }else{
                i++;
            }
        }
        if(encontrado) {
            return ticket;
        }else {
            System.out.println("No se encontro ticket con ese id");
            return null;
        }
    }

    private Ticket seleccinarTicket(ArrayList<Ticket> ticketsList ,String ticketId ){
        Ticket ticketActual = null;
        for (int i = 0; i < ticketsList.size(); i++) {
            if ( ticketsList.get(i).getTicketId().equals(ticketId)) {
                ticketActual = ticketsList.get(i);
            }
        }
        return ticketActual;
    }
    private Cash seleccinarCash(ArrayList<Cash> cashers ,String cashId ){
        Cash casherActual = null;
        for (int i = 0; i < cashers.size(); i++) {
            if ( cashers.get(i).getId().equals(cashId)) {
                casherActual = cashers.get(i);
            }
        }
        return casherActual;
    }


}
