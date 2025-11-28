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
        this.personalizaciones = new String[personalizacion.length];
        for (int i = 0; i < personalizaciones.length; i++) {
            this.personalizaciones[i] = personalizacion[i].replace("--p", "");
        }
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
        Ticket ticketActual = seleccinarTicket();
        if(ticketActual !=null) {
            if (ticketActual.getStatus() != TicketStatus.CLOSE) {
                int cont = 0;
                boolean encontrado = false;
                while (cont < catalog.length() && !encontrado) {
                    if (catalog.find(cont) != null && catalog.find(cont).getID().equals(prodId)) {
                        encontrado = true;
                    } else {
                        cont++;
                    }
                }
                Product p ;
                if(personalizaciones!=null){
                    p=catalog.find(cont).clone();
                    p.setPersonalizaciones(personalizaciones);              }
                else {
                    p=catalog.find(cont);
                }
                if(encontrado&&(p instanceof Events)&&ticketActual.reunionIntroduce(p)){
                    encontrado=false;
                    System.out.println("no puedes introducir dos enetos iguales en el mismo ticket");
                }
                if (encontrado) {
                    if (ticketActual.getStatus() != TicketStatus.OPEN) {
                        ticketActual.setStatus(TicketStatus.OPEN);
                    }
                    for (int i = 0; i < Integer.parseInt(quantity); i++) {
                        ticketActual.AddProduct(p);
                    }
                    System.out.println(ticketActual.toString());
                    System.out.println("ticket add: ok");
                } else {
                    System.out.println("The product was not found");
                }
            } else {
                System.out.println("The ticket was closed");
            }
        }
        else {
            System.out.println("No hay un ticket con el id introducido .");
        }
    }

    private Ticket seleccinarTicket(){
        ArrayList<Ticket> ticketsList = casher.getCashTickets();
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
