package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.Catalog;

import java.util.ArrayList;

public class TicketRemove extends ComandTicket{
    private String prodId;
    private Catalog catalog;
    private Ticket ticketActive;
    public TicketRemove(String prodId, Catalog catalog, Ticket ticketActive){
        this.catalog=catalog;
        this.prodId=prodId;
        this.ticketActive=ticketActive;
    }
    public TicketRemove(String ticketId, String cashId, String prodId, Catalog catalog, ArrayList<Ticket> ticketList, ArrayList<Cash> cashList){
        this.catalog=catalog;
        this.prodId=prodId;
        boolean encontrado=false;
        int count=0;
        while (count<cashList.size()&&!encontrado){
            if(cashList.get(count).getId().equals(cashId)){
                encontrado=true;
                Cash cash=cashList.get(count);
            }
            count++;
        }if(!encontrado){
            System.out.println("That CashId doesn't exist.");
        }
        count=0;
        encontrado=false;
        while(count<ticketList.size()&&!encontrado){
            if (ticketList.get(count).equals(ticketId)){
                encontrado=true;
                ticketActive=ticketList.get(count);
            }
            count++;
        }
        if (!encontrado){
            System.out.println("That TicketId doesn't exist.");
        }
    }
    /**
     * Elimina un producto del ticket.
     * @param prodId    Identificador del producto.
     */
    public void apply(String prodId, Catalog catalog, Ticket ticketActive) {
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
            System.out.println(ticketActive.ToString());
            System.out.println("ticket remove: ok");
        }else{
            System.err.println("This product can't be found");
        }

    }
}
