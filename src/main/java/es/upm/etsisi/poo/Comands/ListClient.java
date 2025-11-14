package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Client;

import java.util.ArrayList;

public class ListClient extends ComandClient {
    public void apply(ArrayList<Client> clients) {
        clients.sort((c1,c2)->c1.getName().compareTo(c2.getName()));
        for (Client client : clients) {
            System.out.println(client.toString());
        }
    }
}
