package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Client;

import java.util.ArrayList;

public class AddClient extends ComandClient {
    public void apply (String name, String DNI, String email, String cashId, ArrayList<Client> clients){
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
}
