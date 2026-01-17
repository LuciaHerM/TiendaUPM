package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.persistence.TicketDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class TicketBussinessNew extends TicketNew{

    private String type;

    public TicketBussinessNew(String ticketId, String cashId, String clientId, String type, Catalog catalog, ArrayList<Cash> cashers, ArrayList<Client> clients) {
        super(ticketId, cashId,clientId,catalog,cashers, clients);
        this.type=type;
    }

    public TicketBussinessNew(String cashId, String clientId, String type, Catalog catalog, ArrayList<Cash> cashers, ArrayList<Client> clients) {
        super(cashId, clientId, catalog, cashers, clients);
        this.type = type;
    }

    public void apply() throws TiendaUPMExcepcion{

        Cash cash = null;
        Client client = null;

        for(int i = 0 ; i < cashers.size();i++){
            Cash ca = cashers.get(i);
            if(ca.getCashId().equals(cashId)){
                cash= ca;
            }
        }
        for(int i = 0 ; i < clients.size();i++){
            Client c = clients.get(i);
            if(c.getId().equals(clientId)){
                client = c;
            }
        }

        if(cash == null) {
            throw new TiendaUPMExcepcion("The cash id can't be find.", "ERR_CASHID");
        }
        else if(client == null){
            throw new TiendaUPMExcepcion("The client id can't be find", "ERR_CLIENTID");
        }
        // Ticket no guarda al cajero ni el cliente
        else {
            if (ticketsList!=null && !existId(ticketId, ticketsList)) {
                CompanyTicketTipe ticketType = CompanyTicketTipe.PRODUCTS_AND_SERVICES;
                ticketType = switch (type){
                    case "-c" -> CompanyTicketTipe.PRODUCTS_AND_SERVICES;
                    case "-p" -> CompanyTicketTipe.PRODUCTS_ONLY;
                    case "-s" -> CompanyTicketTipe.SERVICES_ONLY;
                    default -> CompanyTicketTipe.PRODUCTS_ONLY;
                };
                Ticket ticket = new TicketEmpresa(ticketId, ticketType);
                client.ticketAddClients(ticket);
                cash.ticketAddCash(ticket);
                TicketDAO.save(ticket,type,clientId,cashId);
                System.out.println(ticket.toString());
                System.out.println("ticket new: ok");
            } else{
                throw new TiendaUPMExcepcion("The ticket id is not correct because it does exist the ticket or because the format is not correct:  YY-MM-dd-HH:mm-#####", "ERR_TICKETID");
            }
        }
    }
}
