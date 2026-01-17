package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Product;
import es.upm.etsisi.poo.persistence.TicketDAO;

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
        this.casher = selectCash(cashers,cashId) ;
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
        this.casher = selectCash(cashers,cashId) ;
        this.personalizaciones = null;
    }

    public TicketAdd(String ticketId,String cashId,String prodId, Catalog catalog,ArrayList<Cash> cashers){
        this.ticketId=ticketId;
        this.prodId=prodId;
        this.catalog=catalog;
        this.casher = selectCash(cashers,cashId) ;
        this.personalizaciones = null;
        this.quantity="1";
    }

    /**
     *  Añade una cantidad específica de un producto al ticket.
     */
    public void apply() throws TiendaUPMExcepcion{
        Ticket ticketActual = selectTicket();
        if(ticketActual !=null) {
            if (ticketActual.getStatus() != TicketStatus.CLOSE) {
                int cont = 0;
                boolean find = false;
                while (cont < catalog.length() && !find) {
                    if (catalog.find(cont) != null && catalog.find(cont).getID().equals(prodId)) {
                        find = true;
                    } else {
                        cont++;
                    }
                }
                Product p ;
                if(personalizaciones!=null) {
                    p= ((Personalized) catalog.find(cont)).clone();
                    Personalized p1 = null;
                    if (p instanceof Personalized) {
                        p1 = (Personalized) p;
                        p1.setPersonalizaciones(personalizaciones);
                    }
                    p = p1;
                }
                else {
                    p=catalog.find(cont);
                }
                if(find && (p instanceof Events) && ticketActual.reunionIntroduce(p)) {
                    find=false;
                    throw new TiendaUPMExcepcion("There can't be two identical events.", "ERR_EVENT"){
                    };
                }
                if(find && (p instanceof Services) && (ticketActual instanceof TicketComunes)){
                    Notifier.showErrorAddServicesInComunTicket();
                }else if(find && (p instanceof Services) && (ticketActual instanceof TicketEmpresa)){
                    if (ticketActual.getStatus() != TicketStatus.OPEN) {
                        ticketActual.setStatus(TicketStatus.OPEN);
                        TicketDAO.openTicket(ticketActual.getTicketId());
                    }
                    ticketActual.AddProduct(p);
                    TicketDAO.guardarProductos(ticketActual, p);
                    System.out.println(ticketActual.toString());
                    System.out.println("ticket add: ok");
                }
                else if (find) {
                    if (ticketActual.getStatus() != TicketStatus.OPEN) {
                        ticketActual.setStatus(TicketStatus.OPEN);
                        TicketDAO.openTicket(ticketActual.getTicketId());
                    }
                    if(p instanceof Events) {
                        ((Events) p).setInvited_person(Integer.parseInt(quantity));
                        ticketActual.AddProduct(p);
                        TicketDAO.guardarProductos(ticketActual, p);
                    }else{
                        for (int i = 0; i < Integer.parseInt(quantity); i++) {
                            ticketActual.AddProduct(p);
                            TicketDAO.guardarProductos(ticketActual, p);
                        }
                    }
                    System.out.println(ticketActual.toString());
                    System.out.println("ticket add: ok");
                } else {
                    throw new TiendaUPMExcepcion("The product was not found", "ERR_PRODUCT");
                }
            } else {
                System.out.println("The ticket was closed");
            }
        }
        else {
            throw new TiendaUPMExcepcion("The id can't be find.", "ERR_PRODUCTID");
        }
    }

    private Ticket selectTicket(){
        ArrayList<Ticket> ticketsList = casher.getCashTickets();
        Ticket ticketActual = null;
        for (int i = 0; i < ticketsList.size(); i++) {
            if ( ticketsList.get(i).getTicketId().equals(ticketId)) {
                ticketActual = ticketsList.get(i);
            }
        }
        return ticketActual;
    }
    private Cash selectCash(ArrayList<Cash> cashers , String cashId ){
        Cash casherActual = null;
        for (int i = 0; i < cashers.size(); i++) {
            if ( cashers.get(i).getCashId().equals(cashId)) {
                casherActual = cashers.get(i);
            }
        }
        return casherActual;
    }


}
