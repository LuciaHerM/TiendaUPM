package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class Cash extends User{

    private static String id;
    private static List<Ticket> cashTickets;

    public  Cash (String id, String name, String email){
        Cash.id=id;
        Cash.name=name;
        Cash.email=email;
        Cash.cashTickets = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{class:Cash, id:"+id+", name:'"+name+"', email:"+ email+"}";    }

    public List<Ticket> getCashTickets() {
        return cashTickets;
    }

    public void setCashTickets(List<Ticket> cashTickets) {
        Cash.cashTickets = cashTickets;
    }

    /**
     * Si no está ya el ticket en el ArrayList, lo añade
     * @param ticket Este es el ticket a añadir
     */
    public void ticketAddCash(Ticket ticket){
        if (!cashTickets.contains(ticket)) {
            cashTickets.add(ticket);
        }
    }

    /**
     * Este método recorre printeando todos los tickets del cajero
     */
    public void ticketListCash(){
        for (int i = 0; i < cashTickets.size(); i++) System.out.println(cashTickets.get(i).toString());
    }
}
