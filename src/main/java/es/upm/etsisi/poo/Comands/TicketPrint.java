package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.*;

import es.upm.etsisi.poo.persistence.TicketDAO;

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
    public void apply() throws TiendaUPMExcepcion {
        if(ticketActive!=null) {
            if(permitidoElcierre()) {
                ticketActive.setTicketId(ticketActive.getTicketId() + "-" + LocalDateTime.now().format(FORMAT));
                System.out.println(ticketActive.toString());
                System.out.println("ticket print: ok");
                ticketActive.setStatus(TicketStatus.CLOSE);
                TicketDAO.closeTicket(ticketId);
            }
            else {
                throw new TiendaUPMExcepcion("It is not possible to close a combined company ticket without including at least one product and one service", "ERR_CLOSECOMBINEDTICKET");
            }
        }else {
            throw new TiendaUPMExcepcion("The id of the ticket is not correct", "ERR_TICKETID");
        }
    }

    private boolean permitidoElcierre(){
        boolean cierre = true;
        if(ticketActive instanceof TicketEmpresa){
            cierre = ((TicketEmpresa) ticketActive).validoCierre();
        }
        return cierre;
    }

    private ArrayList<Ticket> listTicketCash() {
        Cash casher = null;
        for (es.upm.etsisi.poo.Cash cash : cashers) {
            if (cash.getCashId().equals(cashId)) {
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
