package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Client;

import java.util.ArrayList;

public class AddClient extends ComandClient {

    private String name;
    private String identifier;
    private String email;
    private String id;
    private ArrayList<Client> clients;

    public AddClient(String name, String identifier, String email, String id, ArrayList<Client> clients) {
        this.name = name;
        this.identifier = identifier;
        this.email = email;
        this.id = id;
        this.clients = clients;
    }
    public void apply (){
        boolean foundIdentifierEnClientList = false;
        int cont = 0;
        while (!foundIdentifierEnClientList && cont < clients.size()){
            if (clients.get(cont).getIdentifier().equals(identifier)){
                foundIdentifierEnClientList = true;
            } else {
                cont++;
            }
        }
        if (!foundIdentifierEnClientList) {
            Client client;
            if (isNIF(identifier)){
                client = new BussinessClient(name, identifier, email, id);
            } else {
                client = new NormalClient(name, identifier, email, id);
            }
            clients.add(client);
            System.out.println(client.toString());
            System.out.println("client add: ok");
        }
        if (foundIdentifierEnClientList){
            System.out.println("That client exist already ");
        }
    }
    private boolean isNIF(String identifier){
        return identifier.matches("[A-Za-z][0-9]{8}");
    }
}
