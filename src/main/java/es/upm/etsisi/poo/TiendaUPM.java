package es.upm.etsisi.poo;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TiendaUPM {

    static Scanner sc = new Scanner(System.in);
    static Ticket ticket;
    static Product[] products;
    static int MAX_NUM_PRODUCTS=200;
    static int num_products;

    public static void main(String[] args) {
        TiendaUPM app = new TiendaUPM();
        app.init();
        app.start();
        app.end();
    }

    private void init() {
        System.out.println("\"Welcome to the ticket module App.");
        ticket = new Ticket();
        products = new Product[MAX_NUM_PRODUCTS];
        num_products=0;
    }

    private void start() {
        boolean continuar = true;
        System.out.println("Ticket module. Type 'help' to see commands.");
        while(continuar){
            System.out.print("tUPM>");
            String comando = sc.nextLine();
            Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");
            Matcher matcher = pattern.matcher(comando);

            String[] comandUni = new String[10];
            int i=0;
            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    comandUni[i] = matcher.group(1); // valor dentro de comillas
                } else {
                    comandUni[i] = matcher.group(2); // palabra normal
                }
                i++;
            }
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
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< num_products){
            if(products[i].getID().equals(id)){
                System.out.println("No se puede introducir un producto con el mismo id de otro ya introducido.");
                encontrado=true;
            }
            i++;
        }
        if(!encontrado && num_products<MAX_NUM_PRODUCTS){
            Category category1 = null;
            switch (category){
                case "MERCH":
                    category1 = Category.MERCH;
                    break;
                case "STATIONERY":
                    category1 = Category.STATIONERY;
                    break;
                case "CLOTHES":
                    category1 = Category.CLOTHES;
                    break;
                case "BOOK":
                    category1 = Category.BOOK;
                    break;
                case "ELECTRONICS":
                    category1 = Category.ELECTRONICS;
                    break;
            }
            double price1 = Double.parseDouble(price);
            Product product = new Product(id,name,category1,price1);
            products[num_products]=product;
            num_products++;
            System.out.println(product.toString());
            System.out.println("prod add: ok");
        }
        else{
            System.err.println("The product can't be add.");
        }
    }

    private void prodList() {
        System.out.println("Catalog:");
        for(int i=0;i<num_products;i++){
            System.out.println(" "+products[i].toString());
        }
        System.out.println("prod list: ok");
    }

    private void prodUpdate(String id, String change, String value) {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< products.length){
            if(products[i].getID().equals(id)){
                encontrado=true;
            }
            else
                i++;
        }
        if(encontrado){
            switch (change){
                case "NAME":
                    products[i].setName(value);
                    break;
                case "CATEGORY":
                    Category category = null;
                    switch (value){
                        case "MERCH":
                            category = Category.MERCH;
                            break;
                        case "STATIONERY":
                            category = Category.STATIONERY;
                            break;
                        case "CLOTHES":
                            category = Category.CLOTHES;
                            break;
                        case "BOOK":
                            category = Category.BOOK;
                            break;
                        case "ELECTRONICS":
                            category = Category.ELECTRONICS;
                            break;
                    }
                    products[i].setCategory(category);
                    break;
                case "PRICE":
                    double price = Double.parseDouble(value);
                    products[i].setPrice(price);
                    break;
            }
            System.out.println(products[i].toString());
            System.out.println("prod update: ok");
        }
    }

    private void prodRemove(String id) {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< products.length){
            if(products[i].getID().equals(id)){
                encontrado=true;
            }
            else
                i++;
        }
        if(encontrado){
            for(int j=i;j<num_products;j++){
                products[j]=products[j+1];
            }
            num_products--;
        }
    }

    private void ticketNew() {
    }

    private void ticketAdd(String prodId, String quantity) {
    }

    private void ticketRemove(String prodId) {
        int i=0;
        boolean encontrado=false;
        Product product;
        while(!encontrado && i<products.length){
            if(products[i].getID().equals(prodId)){
                encontrado=true;
            }
            else{
                i++;
            }
        }
        ticket.RemoveProduct(product=products[i]);
        
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
