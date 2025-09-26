package es.upm.etsisi.poo;


import java.util.Scanner;

public class TiendaUPM {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        TiendaUPM app = new TiendaUPM();
        app.init();
        app.start();
        app.end();
    }

    private void init() {
        System.out.println("\"Welcome to the ticket module App.");
    }

    private void start() {
        boolean continuar = true;
        System.out.println("Ticket module. Type 'help' to see commands.");
        while(continuar){
            System.out.print("tUPM>");
            String comando = sc.nextLine();
            String[] comandUni = comando.split(" ");
            switch (comandUni[0]) {
                case "prod":
                    switch (comandUni[1]) {
                        case "add":
                            prodAdd(comandUni[2], comandUni[3], comandUni[4], comandUni[5]);
                            break;
                        case "list":
                            prodList();
                            break;
                        case "update":
                            prodUpdate(comandUni[2], comandUni[3], comandUni[4]);
                            break;
                        case "remove":
                            prodRemove(comandUni[2]);
                            break;
                        default:
                            unknownCommand();
                            break;
                    }
                    break;
                case "ticket":
                    switch (comandUni[1]) {
                        case "new":
                            ticketNew();
                            break;
                        case "add":
                            ticketAdd(comandUni[2], comandUni[3]);
                            break;
                        case "remove":
                            ticketRemove(comandUni[2]);
                            break;
                        case "print":
                            ticketPrint();
                            break;
                        default:
                            unknownCommand();
                            break;
                    }
                    break;
                case "echo":
                    echo(comandUni);
                    break;
                case "help":
                    printHelp();
                    break;
                case "exit":
                    continuar = false;
                    exitProgram();
                    break;
                default:
                    unknownCommand();
                    break;
            }
        }
    }

    private void printHelp() {
        System.out.println("Commands:\n" +
                "prod add <id> \"<name>\" <category> <price>\n" +
                "prod list\n" +
                "prod update <id> NAME|CATEGORY|PRICE <value>\n" +
                "prod remove <id>\n" +
                "ticket new\n" +
                "ticket add <prodId> <quantity>\n" +
                "ticket remove <prodId>\n" +
                "ticket print\n" +
                "echo \"<texto>\"\n" +
                "help\n" +
                "exit\n\n" +
                "Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS\n" +
                "Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, ELECTRONICS 3%.");
    }

    private void prodAdd(String id, String name, String category, String price) {
    }

    private void prodList() {
    }

    private void prodUpdate(String id, String change, String value) {
    }

    private void prodRemove(String id) {
    }

    private void ticketNew() {
    }

    private void ticketAdd(String prodId, String quantity) {
    }

    private void ticketRemove(String prodId) {
    }

    private void ticketPrint() {
    }

    private void echo(String[] texto) {
        String text = "";
        for(int i=0;i<texto.length;i++){
            text += texto[i] + " ";
        }
        System.out.println(text);
    }

    private static void exitProgram() {
        System.out.println("Closing application.");
    }

    private static void unknownCommand() {
        System.out.println("Comando no reconocido");
    }

    private void end() {
        System.out.println("Goodbye!");
    }
}
