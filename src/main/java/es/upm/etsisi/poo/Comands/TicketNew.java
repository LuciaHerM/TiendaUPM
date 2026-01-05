package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import java.util.ArrayList;

public  class TicketNew extends ComandTicket{
    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm");
    static final Random random = new Random();
    Catalog catalog;
    String cashId;
    ArrayList<Cash> cashers;
    ArrayList<Client> clients;
    String clientId;
    String ticketId;
    ArrayList<Ticket> ticketsList;

    public TicketNew(String ticketId,String cashId, String clientId, Catalog catalog, ArrayList<Cash> cashers, ArrayList<Client> clients){
        this.cashId=cashId;
        this.cashers=cashers;
        this.clients=clients;
        this.clientId=clientId;
        this.catalog=catalog;
        this.ticketId=ticketId;
        this.ticketsList=listTicketCash();
    }

    public TicketNew(String cashId, String clientId, Catalog catalog, ArrayList<Cash> cashers, ArrayList<Client> clients){
        this.cashId=cashId;
        this.cashers=cashers;
        this.clients=clients;
        this.clientId=clientId;
        this.catalog=catalog;
        this.ticketsList=listTicketCash();
        this.ticketId=generateIdTicket(ticketsList);
    }
    /**
     * Le pasan por parametros el id del cajero y el dni del cliente al que pertenece el ticket
     * a los cuales busca en la lista de cajeros y clientes y si los encuentra crea un ticket con esos parametros.
     */
    public void apply() {
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
            if(c.getCashId().equals(clientId)){
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
                Ticket ticket = new TicketComunes(ticketId);
                client.ticketAddClients(ticket);
                cash.ticketAddCash(ticket);
                System.out.println(ticket.toString());
                System.out.println("ticket new: ok");
            } else {
                System.out.println("The ticket id is not correct because it does exist the ticket or because the format is not correct:  YY-MM-dd-HH:mm-#####" );
            }
        }
    }
    public String generateIdTicket(ArrayList<Ticket> ticketsExisting) {

        String date = LocalDateTime.now().format(FORMAT);
        String id;

        do {
            int rnd = 10000 + random.nextInt(90000); // 5 dígitos aleatorios
            id = date + "-" + rnd;
        } while (existId(id, ticketsExisting));

        return id;
    }

    boolean existId(String id, ArrayList<Ticket> tickets) {
        boolean r = false;
        for (Ticket t : tickets) {
            if (t.getTicketId().equals(id)) {
                r = true;
            }
        }
        return r;
    }
    boolean correctFormat(String id) {
        String fecha = "\\d{2}-\\d{2}-\\d{2}-\\d{2}:\\d{2}";
        String apertura = fecha + "-\\d{5}";
        return id.matches(apertura);
    }
    ArrayList<Ticket> listTicketCash() {
        Cash cajero = null;
        for (es.upm.etsisi.poo.Cash cash : cashers) {
            if (cash.getCashId().equals(cashId)) {
                cajero = cash;
            }
        }
        return cajero.getCashTickets();
    }
}
