package es.upm.etsisi.poo;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase TiendaUPM permite gestionar un sistema de compra de productos y de sus tickets.
 */
public class TiendaUPM {


    private static Scanner sc;
    private static Ticket ticket;
    private static Product[] products;
    private static final int MAX_NUM_PRODUCTS = 200;
    private static int num_products;

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
        ticket = new Ticket();
        products = new Product[MAX_NUM_PRODUCTS];
        num_products=0;
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
            Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");
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
        switch (comand[0]) {
            case "client":
                switch (comand[1]) {
                    case "add":

                        break;
                    case "remove":
                        break;
                    case "list":
                        break;
                    default:
                        unknownCommand();
                        break;
                }
                break;
            case "cash":
                switch (comand[1]) {
                    case "add":
                        break;
                    case "remove":
                        break;
                    case "list":
                        break;
                    case "tickets":
                        break;
                    default:
                        unknownCommand();
                        break;
                }
                break;
            case "prod":
                switch (comand[1]) {
                    case "add":
                        prodAdd(comand[2], comand[3], comand[4], comand[5]);
                        break;
                    case "list":
                        prodList();
                        break;
                    case "update":
                        prodUpdate(comand[2], comand[3], comand[4]);
                        break;
                    case "addFood":
                        break;
                    case "addMeeting":
                        break;
                    case "remove":
                        prodRemove(comand[2]);
                        break;
                    default:
                        unknownCommand();
                        break;
                }
                break;
            case "ticket":
                switch (comand[1]) {
                    case "new":
                        ticketNew();
                        break;
                    case "add":
                        ticketAdd(comand[2], comand[3]);
                        break;
                    case "remove":
                        ticketRemove(comand[2]);
                        break;
                    case "print":
                        ticketPrint();
                        break;
                    case "list":
                        break;
                    default:
                        unknownCommand();
                        break;
                }
                break;
            case "echo":
                echo(comand);
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
        System.out.println();
        return continuar;
    }

    /**
     * Imprime los posibles comandos de la app.
     */
    private void printHelp() {
        System.out.println("Commands:\n" +
                "client add \"<nombre>\" <DNI> <email> <cashId>\n" +
                "client remove <DNI>\n" +
                "client list ( incluye el dato del cash que lo creo y ordenados por nombre)\n" +
                "cash add [<id>] \"<nombre>\"<email>\n" +
                "cash remove <id>\n" +
                "cash list\n" +
                "cash tickets <id>\n" +
                "prod add <id> \"<name>\" <category> <price>\n" +
                "prod list\n" +
                "prod update <id> NAME|CATEGORY|PRICE <value>\n" +
                "prod addFood [<id>] \"< name>\" <price> <expiration: yyyy-MM-dd> <max_people>\n" +
                "prod addMeeting [<id>] \"<name>\" <price> < expiration: yyyy-MM-dd> <max_people>\n" +
                "prod remove <id>\n" +
                "ticket new\n" +
                "ticket add <ticketId> <cashId> <prodId> <amount> [--p<txt> --p<txt>]\n" +
                "ticket remove <ticketId><cashId> <prodId>\n" +
                "ticket print <ticketId> <cashId>\n" +
                "ticket list\n" +
                "echo \"<texto>\"\n" +
                "help\n" +
                "exit\n\n" +
                "Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS\n" +
                "Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, ELECTRONICS 3%.");
    }

    /**
     * Añade un nuevo producto al catalogo de la tienda.
     *
     * @param id   Identificador único del producto.
     * @param name  Nombre del producto.
     * @param category  Categoria del producto perteneciente al enum Category.
     * @param price Precio del producto.
     */
    private void prodAdd(String id, String name, String category, String price) {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< num_products){
            if(products[i].getID().equals(id)){
                System.out.println("Can't be add a product with the same id");
                encontrado=true;
            }
            i++;
        }
        if(id!=null && name!=null && category!=null && price!=null) {
            if (!encontrado && num_products < MAX_NUM_PRODUCTS) {
                Category category1 = switch (category) {
                    case "MERCH" -> Category.MERCH;
                    case "STATIONERY" -> Category.STATIONERY;
                    case "CLOTHES" -> Category.CLOTHES;
                    case "BOOK" -> Category.BOOK;
                    case "ELECTRONICS" -> Category.ELECTRONICS;
                    default -> throw new IllegalStateException("Unexpected value: " + category);
                };
                double price1 = Double.parseDouble(price);
                Product product = new Product(id, name, category1, price1);
                products[num_products] = product;
                num_products++;
                System.out.println(product.toString());
                System.out.println("prod add: ok");
            }
        } else {
            System.err.println("The product can't be add.");
        }
    }

    /**
     * Muestra el catálogo de productos actualmente registrados.
     */
    private void prodList() {
        System.out.println("Catalog:");
        for(int i=0;i<num_products;i++){
            System.out.println(" "+products[i].toString());
        }
        System.out.println("prod list: ok");
    }

    /**
     * Permite modificar un atributo de un producto.
     *
     * @param id    Identificador del producto.
     * @param change    Atributo a modificar (name, category o price).
     * @param value     Nuevo valor del atributo.
     */
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
                    Category category = switch (value){
                        case "MERCH" -> Category.MERCH;
                        case "STATIONERY"-> Category.STATIONERY;
                        case "CLOTHES" -> Category.CLOTHES;
                        case "BOOK" -> Category.BOOK;
                        case "ELECTRONICS" -> Category.ELECTRONICS;
                        default -> throw new IllegalStateException("Unexpected value: " + value);
                    };
                    products[i].setCategory(category);
                    break;
                case "PRICE":
                    double price = Double.parseDouble(value);
                    products[i].setPrice(price);
                    break;
            }
            System.out.println(products[i].toString());
            System.out.println("prod update: ok");
        }else{
            System.out.println("Product to update not found");
        }
    }

    /**
     * Elimina un producto del catálogo.
     * @param id    Identificador del producto.
     */
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
            System.out.println("prod remove: ok");
        }else{
            System.out.println("Product to remove not found");
        }
    }

    /**
     * Resetea el ticket en curso.
     */
    private void ticketNew() {
        ticket=new Ticket();
        System.out.println("ticket new: ok");
    }
    /*
    Haces un bucle while para encontrar en el array de productos el ID que nos pasan por parámetro
    //después, cuando lo encuentras te sales del bucle, si no lo encuentra y acaba el array de productos,
    //se sale del bucle, y no añade nada. En caso de que lo encuentre, entra en un bucle, para añadir el
    // producto las veces que se mandan en el String quantity(Argumento). Se usa un Integer.parseInt, para
    // convertir el Str a int, para poder iterar en el bucle.
     */

    /**
    *  Añade una cantidad específica de un producto al ticket.
     *
     * @param prodId    Identificador del producto.
     * @param quantity  Cantidad de unidades a agregar.
     */
    private void ticketAdd(String prodId, String quantity) {
       int cont = 0;
       boolean encontrado = false;
       while (cont < products.length && !encontrado){
           if (products[cont].getID().equals(prodId)){
               encontrado = true;
           }
           else {
               cont++;
           }
       }
       if (encontrado) {
           for (int i = 0; i < Integer.parseInt(quantity); i++) {
               ticket.AddProduct(products[cont]);
           }
           ticketPrint();
           System.out.println("ticket add: ok");
       } else {
           System.err.println("The product was not found");
       }
    }

    /**
     * Elimina un producto del ticket.
     *
     * @param prodId    Identificador del producto.
     */
    private void ticketRemove(String prodId) {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i<products.length){
            if(products[i].getID().equals(prodId)){
                encontrado=true;
            }
            else{
                i++;
            }
        }
        if(encontrado) {
            ticket.RemoveProduct(products[i]);
            ticketPrint();
            System.out.println("ticket remove: ok");
        }else{
            System.err.println("This product can't be found");
        }

    }

    /**
     *  Imprime el ticket actual.
     */
    private void ticketPrint() {
        System.out.println(ticket.ToString());
    }

    /**
     * Imprime el texto del parametro.
     * @param texto Array de String del texto a mostrar.
     */
    private void echo(String[] texto) {
        System.out.println(texto[0] + " \"" + texto[1] + "\"");
    }

    /**
     * Imprime un mensaje al cerrar la app.
     */
    private static void exitProgram() {
        System.out.println("Closing application.");
    }

    /**
     * Imprime un mensaje cuando el usuario introduce un comando desconocido.
     */
    private static void unknownCommand() {
        System.out.println("Command not found");
    }

    /**
     * Finaliza la ejecucion de la app mostrando un mensaje de despedida.
     */
    private void end() {
        System.out.println("Goodbye!");

    }
}
