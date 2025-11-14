package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.Catalog;

public class TicketAdd extends ComandTicket{
    /**
     *  Añade una cantidad específica de un producto al ticket.
     * @param prodId    Identificador del producto.
     * @param quantity  Cantidad de unidades a agregar.
     */
    public void apply(String prodId, String quantity, Catalog catalog, Ticket ticketActive) {
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
}
