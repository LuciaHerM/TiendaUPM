package es.upm.etsisi.poo;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase TiendaUPM permite gestionar un sistema de compra de productos y de sus tickets.
 */
public class TiendaUPM {


    private static Scanner sc;
    private static Ticket ticketActive;
    private static Catalog catalog;
    private static ArrayList<Cash> cashers;
    private static ArrayList<Client> clients;

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
        ticketActive = new Ticket();
        catalog = new Catalog();
        cashers =new ArrayList<Cash>();
        clients =new ArrayList<Client>();
        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
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
        switch (comand[0]) {
            case "client":
                switch (comand[1]) {
                    case "add":
                        clientAdd(comand[2], comand[3], comand[4], comand[5]);
                        break;
                    case "remove":
                        clientRemove(comand[2]);
                        break;
                    case "list":
                        clientList();
                        break;
                    default:
                        unknownCommand();
                        break;
                }
                break;
            case "cash":
                switch (comand[1]) {
                    case "add":
                        if (comand.length == 5) {
                            cashAdd(comand[2], comand[3], comand[4]);
                        } else { cashAdd(comand[2], comand[3]);}
                        break;
                    case "remove":
                        cashRemove(comand[1]);
                        break;
                    case "list":
                        cashList();
                        break;
                    case "tickets":
                        cashTickets(comand[2]);
                        break;
                    default:
                        unknownCommand();
                        break;
                }
                break;
            case "prod":
                switch (comand[1]) {
                    case "add":
                        if(comand.length==5)
                            prodAdd(comand[2], comand[3], comand[4]);
                        else if(comand.length==7)
                            prodAddPer(comand[2], comand[3], comand[4],comand[5],comand[6]);
                        else{
                            if(comand[2].charAt(0)=='"'){
                                prodAddPer(comand[2], comand[3], comand[4],comand[5]);
                            }else {
                                prodAdd(comand[2], comand[3], comand[4], comand[5]);
                            }

                        }
                        break;
                    case "list":
                        prodList();
                        break;
                    case "update":
                        prodUpdate(comand[2], comand[3], comand[4]);
                        break;
                    case "addFood":
                        if(comand.length==7)
                            prodAddFood(comand[2],comand[3],comand[4],comand[5],comand[6]);
                        else
                            prodAddFood(comand[2],comand[3],comand[4],comand[5]);
                        break;
                    case "addMeeting":
                        if(comand.length==7)
                            prodAddMeeting(comand[2],comand[3],comand[4],comand[5],comand[6]);
                        else
                            prodAddMeeting(comand[2],comand[3],comand[4],comand[5]);
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
                "ticket new [<id>] <cashId> <userId>\n" +
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
     * Recorre la ArrayList para ver si hay un cliente con el DNI que nos pasa,
     * si existe, devuelve un error, y no lo añade,
     * si no existe lo crea y añade a la ArrayList de clientes
     * @param name nombre del cliente
     * @param DNI DNI del cliente
     * @param email email del cliente
     * @param cashId id del cajero
     */
    private void clientAdd(String name, String DNI, String email, String cashId) {
        boolean encuentraDNIEnClientList = false;
        int cont = 0;
        while (!encuentraDNIEnClientList && cont < clients.size()){
            if (clients.get(cont).getDNI().equals(DNI)){
                encuentraDNIEnClientList = true;
            } else {
                cont++;
            }
        }
        if (!encuentraDNIEnClientList) {
            Client client = new Client(name, DNI, email, cashId);
            clients.add(client);
        }
        if (encuentraDNIEnClientList){
            System.err.println("Ya existe un cliente con dicho DNI");
        }
    }
    /**
     * Recorre la ArrayList para ver si hay un cliente con el DNI que nos pasa,
     * si existe, lo elimina, y si no devuelve un error.
     * @param DNI DNI del cliente a eliminar
     */
    private void clientRemove(String DNI){
        boolean encontrarEnListaEliminar = false;
        int contador = 0;
        while (!encontrarEnListaEliminar && contador < clients.size()){
            if (clients.get(contador).getDNI().equals(DNI)){
                encontrarEnListaEliminar = true;
            } else { contador++;}
        }
        if (encontrarEnListaEliminar){
            clients.remove(clients.get(contador));
        } else {
            System.err.println("No existe dicho cliente, por tanto no se puede eliminar");
        }
    }
    /**
     * Este método recorre la lista de los clientes, y los va printeando en orden
     */
    private void clientList(){
        clients.sort((c1,c2)->c1.getName().compareTo(c2.getName()));
        for (Client client : clients) {
            System.out.println(client.toString());
        }
    }
    /**
     * Comprueba que no esiste un cajero con el mismo nombre y si no esiste crea
     * un cajero y o añade al arrayList de cajeros
     */
    private void cashAdd(String name, String email, String id){
        boolean encontrado = false;
        int i = 0 ;
        while (!encontrado&&i<cashers.size()) {
            if (cashers.get(i).getId().equals(id)) {
               encontrado = true;
            }
            i++;
        }
        if(!encontrado) {
            Cash cash = new Cash(id, name, email);
            cashers.add(cash);
        }else {
            System.err.println("Ya existe un cajero con el mismo Id en la base de datos");
        }
    }
    /**
     * Busca el mayor id para crear un nuevo el cual será mayorId + 1 el cual sabemos
     * que no va a exitir
     */
    private void cashAdd(String name, String email){
        int nuevoId = 0 ;
        int maximo = 0;
        for (int i = 0 ; i < cashers.size() ; i++){
            if ( maximo < Integer.parseInt(cashers.get(i).getId())){
                maximo = Integer.parseInt(cashers.get(i).getId());
            }
        }
        Cash cash = new Cash(String.valueOf(maximo+1),name,email);
        cashers.add(cash);
    }
    /**
     * Busca el ID dentro del arrayList de elementos y si lo encuentra elimina el cajero, en cambio,
     * si no lo encuentra dispara un mensaje de error de que el ID no es correcto.
     */
    private void cashRemove(String id){
        boolean encontrado = false;
        int i =0;
        while(i < cashers.size()&&!encontrado){
            if (cashers.get(i).getId().equals(id)) {
                encontrado = true;
            }
            else {
                i++;
            }
        }
        if(encontrado){
            cashers.remove(i);
        }
        else {
            System.err.println("El id introducido no se encuentra en la base de datos del sistema");
        }

    }
    /**
     * Realiza un bucle para ir mostrando en pantalla los datos de cada cajero guardado en el arrayList
     */
    private void cashList(){
        cashers.sort((c1,c2)->c1.getName().compareTo(c2.getName()));
        for(int i = 0 ; i < cashers.size();i++){
            System.out.println(cashers.get(i).toString());
        }
    }
    /**
     *
     */
    private void cashTickets(String id){
        Cash cajero=null;
        for(Cash cash:cashers){
            if(cash.getId().equals(id)){
                cajero=cash;
            }
        }
        if(cajero!=null){
            cajero.ticketListCash();
        }else{
            System.out.println("The casher was not found");
        }
    }

    /**
     * Añade un nuevo producto al catálogo de la tienda.
     */
    private void prodAdd(String id, String name, String category, String price) {
        catalog.add(id,name,category,price);
    }

    private void prodAdd(String name, String category, String price) {
        String id=catalog.crearId();
        catalog.add(id,name,category,price);
    }


    private void prodAddPer(String id, String name, String category, String price, String pers) {

   }

    private void prodAddPer(String name, String category, String price, String pers) {

   }

    /**
     * Muestra el catálogo de productos actualmente registrados.
     */
    private void prodList() {
        catalog.list();
    }

    /**
     * Permite modificar un atributo de un producto.
     */
    private void prodUpdate(String id, String change, String value) {
        catalog.update(id,change,value);
    }

    /**
     * Elimina un producto del catálogo.
     */
    private void prodRemove(String id) {
        catalog.remove(id);
    }
    /**
     * Añade un producto de tipo food llamando al metodo dentro de catalogo
     * @param id es el id del producto
     * @param name es el nombre del producto
     * @param price es el precio del producto
     * @param expiration_day es el dia que se quiere la comida
     * @param num_person es el numero de personas para las que se quire la comida
     */
    private void prodAddFood(String id, String name, String price, String expiration_day, String num_person){
        catalog.addEvent(id, name,price,expiration_day,num_person,TypeEvent.FOOD);
    }


    private void prodAddFood(String name, String price, String expiration_day, String num_person) {
        String id=catalog.crearId();
        catalog.addEvent(id, name,price,expiration_day,num_person,TypeEvent.FOOD);
    }


    /**
     * Añade un producto de tipo reunion llamando al metodo dentro de catalogo
     * @param id es el id del producto
     * @param name es el nombre del producto
     * @param price es el precio del producto
     * @param expiration_day es el dia que se quiere la reunion
     * @param num_person es el numero de personas que van a acceder a la reunion
     */
    private void prodAddMeeting(String id, String name, String price, String expiration_day, String num_person){
        catalog.addEvent(id, name,price,expiration_day,num_person,TypeEvent.MEETING);
    }
    private void prodAddMeeting(String name, String price, String expiration_day, String num_person){
        String id=catalog.crearId();
        catalog.addEvent(id, name,price,expiration_day,num_person,TypeEvent.MEETING);
    }


    /**
     * Resetea el ticket en curso.
     */
    private void ticketNew() {
        ticketActive =new Ticket();
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
     * @param prodId    Identificador del producto.
     * @param quantity  Cantidad de unidades a agregar.
     */
    private void ticketAdd(String prodId, String quantity) {
       int cont = 0;
       boolean encontrado = false;
       while (cont < catalog.length() && !encontrado){
           if (catalog.find(cont)!=null && catalog.find(cont).getID().equals(prodId)){
               encontrado = true;
           }
           else {
               cont++;
           }
       }
       if (encontrado) {
           for (int i = 0; i < Integer.parseInt(quantity); i++) {
               ticketActive.AddProduct(catalog.find(cont));
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
        while(!encontrado && i< catalog.length()){
            if(catalog.find(i)!=null && catalog.find(i).getID().equals(prodId)){
                encontrado=true;
            }
            else{
                i++;
            }
        }
        if(encontrado) {
            ticketActive.RemoveProduct(catalog.find(i));
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
        System.out.println(ticketActive.ToString());
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
