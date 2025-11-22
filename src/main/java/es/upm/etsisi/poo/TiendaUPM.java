package es.upm.etsisi.poo;


import es.upm.etsisi.poo.Comands.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase TiendaUPM permite gestionar un sistema de compra de productos y de sus tickets.
 */
public class TiendaUPM {


    private static Scanner sc;
    private Ticket ticketActive;
    private Catalog catalog;
    private ArrayList<Cash> cashers;
    private ArrayList<Client> clients;
    private ArrayList<Ticket> ticketList;

    /**
     * Es el método principal de ejecucion de la aplicación. Funciona tanto al proporcionar un archivo como argumento
     * (los comandos se leen desde él) o se utiliza la entrada estándar.
     *
     * @param args  Argumentos de la línea de comandos.
     * @throws FileNotFoundException    Se lanza una excepcion si el archivo proporcionado no existe.
     */
    public static void main(String[] args) throws FileNotFoundException {
        TiendaUPM app = new TiendaUPM();
        if(args.length>0){
            sc = new Scanner(new File(args[0]));
        }else{
            sc = new Scanner(System.in);
        }
        app.init();
        app.start();
        app.end();
    }

    /**
     * Muestra el mensaje de bienvenida de la app, inicializa el ticket y el array de productos.
     */
    private void init() {
        System.out.println("Welcome to the ticket module App.");
        catalog = new Catalog();
        cashers =new ArrayList<Cash>();
        clients =new ArrayList<Client>();
        ticketList = new ArrayList<Ticket>();
    }

    /**
     * Inicia el bucle principal del programa, donde se procesan los comandos.
     */
    private void start() {
        boolean continuar = true;
        System.out.println("Ticket module. Type 'help' to see commands.");
        while (continuar) {
            System.out.print("tUPM>");
            String comando = sc.nextLine();
            Pattern pattern = Pattern.compile("\"([^\"]*(?:\"[^\"]*)*)\"|(\\S+)");
            Matcher matcher = pattern.matcher(comando);

            String[] comand = new String[10];
            int i=0;
            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    comand[i] = matcher.group(1); // valor dentro de comillas
                } else {
                    comand[i] = matcher.group(2); // palabra normal
                }
                i++;
            }
            continuar=gestionComandos(comand);
        }
    }

    /**
     * Un metodo para gestionar los comandos que recibe
     * @param comand
     * @return
     */
    private boolean gestionComandos(String[] comand){
        boolean continuar=true;
        Comands comad = null;
        switch (comand[0]) {
            case "client":
                switch (comand[1]) {
                    case "add":
                        comad= new AddClient(comand[2], comand[3], comand[4], comand[5], clients);
                        break;
                    case "remove":
                        comad = new RemoveClient(comand[2], clients);
                        break;
                    case "list":
                        comad = new ListClient(clients);
                        break;
                }
                break;
            case "cash":
                switch (comand[1]) {
                    case "add":
                        if (comand.length == 5) {
                            comad = new ComandCashAdd(comand[2], comand[3], comand[4], cashers);
                        } else { comad = new ComandCashAdd(comand[2], comand[2], comand[3], cashers);}
                        break;
                    case "remove":
                        comad = new ComandCashRemove(comand[1], cashers, ticketList);
                        break;
                    case "list":
                        comad = new ComandCashList(cashers);
                        break;
                    case "tickets":
                        comad = new ComandCashTickets(comand[2], cashers,ticketList);
                        break;
                }
                break;
            case "prod":
                switch (comand[1]) {
                    case "add":
                        if(comand.length==5)
                            comad = new ProdAdd(comand[2], comand[3], comand[4], catalog);
                        else if(comand.length==7)
                            comad = new ProdAddPer(comand[2], comand[3], comand[4],comand[5],comand[6], catalog);
                        else{
                            if(comand[2].charAt(0)=='"'){
                                comad = new ProdAdd(comand[2], comand[3], comand[4],comand[5], catalog);
                            }else {
                                comad = new ProdAddPer(comand[2], comand[3], comand[4], comand[5],catalog);
                            }

                        }
                        break;
                    case "list":
                        comad = new ProdList(catalog);
                        break;
                    case "update":
                        comad = new ProdUpdate(comand[2], comand[3], comand[4], catalog);
                        break;
                    case "addFood":
                        if(comand.length==7)
                            comad = new ProdAddFood(comand[2],comand[3],comand[4],comand[5],comand[6], catalog);
                        else
                            comad = new ProdAddFood(comand[2],comand[3],comand[4],comand[5], catalog);
                        break;
                    case "addMeeting":
                        if(comand.length==7)
                            comad = new ProdAddMeeting(comand[2],comand[3],comand[4],comand[5],comand[6], catalog);
                        else
                            comad = new ProdAddMeeting(comand[2],comand[3],comand[4],comand[5], catalog);
                        break;
                    case "remove":
                        comad = new ProdRemove(comand[2], catalog);
                        break;
                }
                break;
            case "ticket":
                switch (comand[1]) {
                    case "new":
                        if (comand.length == 4) {
                            comad = new TicketNew(comand[2], comand[3], catalog, cashers, clients, ticketList);
                        }
                        else if(comand.length == 5){
                            comad = new TicketNew(comand[2], comand[3],comand[4], catalog, cashers, clients, ticketList);
                        }
                        break;
                    case "add":
                        comad = new TicketAdd(comand[2], comand[3], catalog, ticketActive);
                        break;
                    case "remove":
                        if(comand.length==3){
                            comad = new TicketRemove(comand[2], catalog, ticketActive);
                        } else if (comand.length==5) {
                            comad = new TicketRemove(comand[2],comand[3],comand[4], catalog, ticketList, cashers);

                        }
                        break;
                    case "print":
                        comad = new TicketPrint(ticketActive, ticketList);
                        break;
                    case "list":
                        break;
                }
                break;
            case "echo":
                comad = new ComandEcho(comand);
                break;
            case "help":
                comad = new ComandsExit();
                break;
            case "exit":
                continuar = false;
                comad = new ComandsExit();
                break;
            default:
                comad = new UnknownComand();
                break;
        }
        comad.apply();
        System.out.println();
        return continuar;
    }

    /**
     * Finaliza la ejecucion de la app mostrando un mensaje de despedida.
     */
    private void end() {
        System.out.println("Goodbye!");

    }
}
