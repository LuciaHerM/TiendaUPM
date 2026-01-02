package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.BussinessClient;
import es.upm.etsisi.poo.Client;
import es.upm.etsisi.poo.NormalClient;

import java.util.ArrayList;

public class AddClient extends ComandClient {

    private String name;
    private String id;
    private String email;
    private String cashId;
    private ArrayList<Client> clients;

    public AddClient(String name, String id, String email, String cashId, ArrayList<Client> clients) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.cashId = cashId;
        this.clients = clients;
    }
    public void apply (){
        boolean foundIDEnClientList = false;
        int cont = 0;
        while (!foundIDEnClientList && cont < clients.size()){
            if (clients.get(cont).getCashId().equals(id)){
                foundIDEnClientList = true;
            } else {
                cont++;
            }
        }
        if (!foundIDEnClientList) {
            Client client;
            if (id.matches("[A-Za-z][0-9]{8}")){
                client = new BussinessClient(name, id, email, cashId);
            } else {
                client = new NormalClient(name, id, email, cashId);
            }
            clients.add(client);
            System.out.println(client.toString());
            System.out.println("client add: ok");
        }
        if (foundIDEnClientList){
            System.out.println("That client exist already ");
        }
    }
}
