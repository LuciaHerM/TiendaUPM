package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class Client extends User{

    private String DNI;
    private String cashId;
    private List<Ticket> clientTickets;

    public Client (String name, String DNI, String email, String cashId){
        super(name, email);
        this.DNI=DNI;
        this.cashId=cashId;
        this.clientTickets = new ArrayList<>();
    }

    public String getDNI() {
        return DNI;
    }

    public String getCashId() {
        return cashId;
    }

    @Override
    public String toString() {
        return "{class:Cliente, name:'"+name+"', DNI:"+DNI+", email:"+ email+", cashId:"+ cashId+"}";
    }

    public List<Ticket> getClientTickets() {
        return clientTickets;
    }

    public void setClientTickets(List<Ticket> clientTickets) {
        this.clientTickets = clientTickets;
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
    public void ticketListClientes(){
        for (int i = 0; i < clientTickets.size(); i++) System.out.println(clientTickets.get(i).toString());
    }
}
