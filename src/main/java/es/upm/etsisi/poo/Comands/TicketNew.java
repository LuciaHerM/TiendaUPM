package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.Client;
import es.upm.etsisi.poo.Ticket;

import java.util.ArrayList;

public  class TicketNew extends ComandTicket{
    private Catalog catalog;
    private String cashId;
    private ArrayList<Cash> cashers;
    private ArrayList<Client> clients;
    private String clientId;

    public TicketNew(String cashId, String clientId, Catalog catalog, ArrayList<Cash> cashers, ArrayList<Client> clients, Ticket ticketActive){
        this.cashId=cashId;
        this.cashers=cashers;
        this.clients=clients;
        this.clientId = clientId;
        this.catalog=catalog;
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
            System.err.println("El id del cajero no se encuntra en nuestra base de datos.");
        }
        if(client == null) {
            System.err.println("El dni del cliente introducido no se encuentra en nuestra base de datos.");
        }
        // Ticket no gurarda al cajero ni el cliente
        Ticket ticket = new Ticket();
        client.ticketAddClients(ticket);
        cash.ticketAddCash(ticket);
        System.out.println("ticket new: ok");
    }
}
