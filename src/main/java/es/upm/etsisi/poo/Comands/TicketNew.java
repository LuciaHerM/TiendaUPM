package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.Client;
import es.upm.etsisi.poo.Ticket;

import java.util.ArrayList;

public  class TicketNew extends ComandTicket{
    /**
     * Le pasan por parametros el id del cajero y el dni del cliente al que pertenece el ticket
     * ls cuales los busca en la lista de cajeros y clientes y si los encuentra crea un ticket con esos parametros .
     */
    public void apply(String cashId, String clientId, Catalog catalog, ArrayList<Cash> cashers, ArrayList<Client> clients, Ticket ticketActive) {
        Cash cajero = null;
        Client cliente = null;

        for(int i = 0 ; i < cashers.size();i++){
            if(cashId.equals(cashers.get(i).getId())){
                cajero=cashers.get(i);
            }
        }
        for(int i = 0 ; i < clients.size();i++){
            if(cashId.equals(clients.get(i).getDNI())){
                cliente=clients.get(i);
            }
        }
        if(cajero!=null){
            if(cliente!=null){
                ticketActive=new Ticket(cajero,cliente);
                System.out.println("ticket new: ok");
            }
            else {
                System.err.println("El dni del cliente introducido no se encuentra en nuestra base de datos .");
            }
        }
        else {
            System.err.println("El id del cajero no se encuntra en nuestra base de datos .");
        }
    }
}
