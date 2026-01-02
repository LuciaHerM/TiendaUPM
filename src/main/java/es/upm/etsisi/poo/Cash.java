package es.upm.etsisi.poo;

import java.util.ArrayList;

public class Cash extends User{

    private String cashId;
    private ArrayList<Ticket> cashTickets;

    public Cash (String id, String name, String email){
        super(name, email, id);
        this.cashId = id;
        this.cashTickets = new ArrayList<>();
    }

    public String getCashId() {
        return cashId;
    }

    public ArrayList<Ticket> getCashTickets() {
        return cashTickets;
    }

    public void setCashTickets(ArrayList<Ticket> cashTickets) {
        this.cashTickets = cashTickets;
    }


    @Override
    public String toString() {
        return "Cash{identifier='" + cashId +"', name='"+name+"', email='"+ email+"'}";    }


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
        System.out.println("Cash:\n");
        for (int i = 0; i < cashTickets.size(); i++) System.out.println(" " + cashTickets.get(i).toString());
    }
}
