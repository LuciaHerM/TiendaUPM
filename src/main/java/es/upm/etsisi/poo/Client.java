package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class Client extends User{

    private static String DNI;
    private static String cashId;
    private static List<Ticket> clientTickets;

    public Client (String name, String DNI, String email, String cashId){
        Client.name=name;
        Client.DNI=DNI;
        Client.email=email;
        Client.cashId=cashId;
        Client.clientTickets = new ArrayList<>();
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
        Client.clientTickets = clientTickets;
    }
    /**
     * Si no está ya el ticket en el ArrayList, lo añade
     * @param clientTickets ArrayList de tickets de este cliente
     * @param ticket Este es el ticket a añadir
     */
    public void ticketAddClients(List<Ticket> clientTickets, Ticket ticket){
        if (!clientTickets.contains(ticket)) {
            clientTickets.add(ticket);
        }
    }

    /**
     * Este método recorre printeando todos los tickets del cliente
     * @param clientTickets El ArrayList de tickets de este cliente
     */
    public void ticketListClientes(List<Ticket> clientTickets){
        for (int i = 0; i < clientTickets.size(); i++) System.out.println(clientTickets.get(i).toString());
    }
}
