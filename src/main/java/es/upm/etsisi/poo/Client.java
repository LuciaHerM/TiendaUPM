package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class Client extends User{

    private String id;
    private String cashId;
    private List<Ticket> clientTickets;

    public Client (String name, String id, String email, String cashId){
        super(name, email, id);
        this.id = id;
        this.cashId=cashId;
        this.clientTickets = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getCashId() {
        return cashId;
    }

    public List<Ticket> getClientTickets() {
        return clientTickets;
    }

    public void setClientTickets(List<Ticket> clientTickets) {
        this.clientTickets = clientTickets;
    }

    @Override
    public String toString() {
        return "USER{identifier='"+id+"', name='"+name+"', email='"+ email+"', cash="+ cashId+"}";
    }

    /**
     * Si no está ya el ticket en el ArrayList, lo añade
     * @param ticket Este es el ticket a añadir
     */
    public void ticketAddClients(Ticket ticket){
        if (!clientTickets.contains(ticket)) {
            clientTickets.add(ticket);
        }
    }

    /**
     * Este método recorre printeando todos los tickets del cliente
     */
    public void ticketListClients(){
        System.out.println("Client:\n");
        for (int i = 0; i < clientTickets.size(); i++) System.out.println(clientTickets.get(i).toString());
    }
}
