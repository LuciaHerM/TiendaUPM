package es.upm.etsisi.poo;


import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        App app = new App();
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
