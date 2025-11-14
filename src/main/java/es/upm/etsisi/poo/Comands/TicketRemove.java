package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.Catalog;

public class TicketRemove extends ComandTicket{
    @Override
    /**
     * Elimina un producto del ticket.
     * @param prodId    Identificador del producto.
     */
    public void apply(String prodId) {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< catalog.length()){
            if(catalog.find(i)!=null && catalog.find(i).getID().equals(prodId)){
                encontrado=true;
            }
            else{
                i++;
            }
        }
        if(encontrado) {
            ticketActive.RemoveProduct(catalog.find(i));
            ticketPrint();
            System.out.println("ticket remove: ok");
        }else{
            System.err.println("This product can't be found");
        }

    }
}
