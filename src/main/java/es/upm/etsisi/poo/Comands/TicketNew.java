package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.Client;
import es.upm.etsisi.poo.Ticket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import java.util.ArrayList;

public  class TicketNew extends ComandTicket{
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm");
    private static final Random random = new Random();
    private Catalog catalog;
    private String cashId;
    private ArrayList<Cash> cashers;
    private ArrayList<Client> clients;
    private String clientId;
    private String ticketId;
    private ArrayList<Ticket> ticketsList;

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
        this.ticketId=generarIdTicket(ticketsList);
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
            if(ca.getId().equals(cashId)){
                cash= ca;
            }
        }
        for(int i = 0 ; i < clients.size();i++){
            Client c = clients.get(i);
            if(c.getDNI().equals(clientId)){
                client = c;
            }
        }

        if(cash == null) {
            System.err.println("El id del cajero no se encuentra en nuestra base de datos.");
        }
        else if(client == null) {
            System.err.println("El dni del cliente introducido no se encuentra en nuestra base de datos.");
        }
        // Ticket no guarda al cajero ni el cliente
        else {
            if (ticketsList!=null && !existeId(ticketId, ticketsList)) {
                Ticket ticket = new Ticket(ticketId);
                client.ticketAddClients(ticket);
                cash.ticketAddCash(ticket);
                System.out.println("ticket new: ok");
            } else {
                System.err.println("El id del ticket no es correcto bien por que ya esiste un ticket con el mismo id o bien porque el formato no es correcto :  YY-MM-dd-HH:mm-#####" );
            }
        }
    }
    public String generarIdTicket(ArrayList<Ticket> ticketsExistentes) {

        String fecha = LocalDateTime.now().format(FORMAT);
        String id;

        do {
            int rnd = 10000 + random.nextInt(90000); // 5 dígitos aleatorios
            id = fecha + "-" + rnd;
        } while (existeId(id, ticketsExistentes));

        return id;
    }

    private boolean existeId(String id, ArrayList<Ticket> tickets) {
        boolean r = false;
        for (Ticket t : tickets) {
            if (t.getTicketId().equals(id)) {
                r = true;
            }
        }
        return r;
    }
    private boolean formatoIDCorrecto(String id) {
        String fecha = "\\d{2}-\\d{2}-\\d{2}-\\d{2}:\\d{2}";
        String apertura = fecha + "-\\d{5}";
        return id.matches(apertura);
    }
    private ArrayList<Ticket> listTicketCash() {
        Cash cajero = null;
        for (es.upm.etsisi.poo.Cash cash : cashers) {
            if (cash.getId().equals(cashId)) {
                cajero = cash;
            }
        }
        return cajero.getCashTickets();
    }
}
