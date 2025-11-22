package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.Catalog;

import java.util.ArrayList;

public class TicketAdd extends ComandTicket{

    private String prodId;
    private String quantity;
    private Catalog catalog;
    private Ticket ticketActive;
    private String ticketId;
    /*private ArrayList<Ticket> ticketsList;
    ArrayList<String> personalizacion */;
    public TicketAdd(String prodId, String quantity,Catalog catalog,Ticket ticketActive){
        this.prodId=prodId;
        this.quantity=quantity;
        this.catalog=catalog;
        this.ticketActive=ticketActive;
    }
    /**
     *  Añade una cantidad específica de un producto al ticket.
     */
    public void apply() {
        int cont = 0;
        boolean encontrado = false;
        while (cont < catalog.length() && !encontrado){
            if (catalog.find(cont)!=null && catalog.find(cont).getID().equals(prodId)){
                encontrado = true;
            }
            else {
                cont++;
            }
        }
        if (encontrado) {
            for (int i = 0; i < Integer.parseInt(quantity); i++) {
                ticketActive.AddProduct(catalog.find(cont));
            }
            System.out.println(ticketActive.toString());
            System.out.println("ticket add: ok");
        } else {
            System.err.println("The product was not found");
        }
    }
    /*public Ticket seleccinarTicket(ArrayList<Ticket> ticketsList ,String ticketId ){
        Ticket ticketActual = null;
        for (int i = 0; i < ticketsList.size(); i++) {
            if ( ticketsList.get(i).getId().equals(ticketId)) {
                ticketActual = ticketsList.get(i);
            }
        }
        return ticketActual;
    }

     */
}
