package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public abstract class Client extends User{

    private List<Ticket> clientTickets;

    public Client (String name, String email, String id){
        super(name, email, id);
        this.clientTickets = new ArrayList<>();
    }
    public abstract String getIdentifier();
    public abstract boolean isCompany();

    public List<Ticket> getClientTickets() {
        return clientTickets;
    }

    public void setClientTickets(List<Ticket> clientTickets) {
        this.clientTickets = clientTickets;
    }

    @Override
    public String toString() {
        return "Client{identifier='"+DNI+"', name='"+name+"', email='"+ email+"', cash="+ id +"}";
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
