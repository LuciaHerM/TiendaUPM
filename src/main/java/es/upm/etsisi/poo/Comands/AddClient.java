package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Client;

import java.util.ArrayList;

public class AddClient extends ComandClient {

    private String name;
    private String DNI;
    private String email;
    private String cashId;
    private ArrayList<Client> clients;

    public AddClient(String name, String DNI, String email, String cashId, ArrayList<Client> clients) {
        this.name = name;
        this.DNI = DNI;
        this.email = email;
        this.cashId = cashId;
        this.clients = clients;
    }
    public void apply (){
        boolean foundDNIEnClientList = false;
        int cont = 0;
        while (!foundDNIEnClientList && cont < clients.size()){
            if (clients.get(cont).getDNI().equals(DNI)){
                foundDNIEnClientList = true;
            } else {
                cont++;
            }
        }
        if (!foundDNIEnClientList) {
            Client client = new Client(name, DNI, email, cashId);
            clients.add(client);
            System.out.println(client.toString());
            System.out.println("client add: ok");
        }
        if (foundDNIEnClientList){
            System.out.println("That client exist already ");
        }
    }
}
