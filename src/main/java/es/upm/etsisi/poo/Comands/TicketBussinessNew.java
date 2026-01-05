package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.*;

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

    public void apply(){

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
            System.out.println("The cash id can't be find.");
        }
        else if(client == null) {
            System.out.println("The client id can't be find");
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
                System.out.println(ticket.toString());
                System.out.println("ticket new: ok");
            } else {
                System.out.println("The ticket id is not correct because it does exist the ticket or because the format is not correct:  YY-MM-dd-HH:mm-#####" );
            }
        }
    }
}
