package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.TicketStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class TicketPrint extends ComandTicket{
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm");
    private Ticket ticketActive;
    private ArrayList<Ticket> ticketList;
    private String ticketId;
    private String cashId;
    private ArrayList<Cash> cashers;

    public TicketPrint(String ticketId, String cashId, ArrayList<Cash> cashers){
        this.ticketId=ticketId;
        this.cashId=cashId;
        this.cashers=cashers;
        this.ticketList=listTicketCash();
        if(ticketList!=null) {
            this.ticketActive = selectTicket();
        }
    }
    /**
     *  Imprime el ticket actual y guarda ticket .
     */
    public void apply() {
        if(ticketActive!=null) {
            ticketActive.setTicketId(ticketActive.getTicketId()+"-"+LocalDateTime.now().format(FORMAT));
            System.out.println(ticketActive.toString());
            System.out.println("ticket print: ok");
            ticketActive.setStatus(TicketStatus.CLOSE);
        }else{
            System.out.println("The id of the ticket is not correct");
        }
    }

    private ArrayList<Ticket> listTicketCash() {
        Cash casher = null;
        for (es.upm.etsisi.poo.Cash cash : cashers) {
            if (cash.getId().equals(cashId)) {
                casher = cash;
            }
        }
        if(casher!=null) {
            return casher.getCashTickets();
        }
        else {
            return null;
        }
    }

    private Ticket selectTicket(){
        Ticket ticketActual = null;
        for (int i = 0; i < ticketList.size(); i++) {
            if ( ticketList.get(i).getTicketId().equals(ticketId)) {
                ticketActual = ticketList.get(i);
            }
        }
        return ticketActual;
    }
}
