package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Client;

import java.util.ArrayList;

public class RemoveClient extends ComandClient {
    private String DNI;
    private ArrayList<Client> clients;
    public RemoveClient(String DNI, ArrayList<Client> clients) {
        this.DNI = DNI;
        this.clients = clients;
    }

    public void apply(String DNI, ArrayList<Client> clients) {
        boolean encontrarEnListaEliminar = false;
        int contador = 0;
        while (!encontrarEnListaEliminar && contador < clients.size()) {
            if (clients.get(contador).getDNI().equals(DNI)) {
                encontrarEnListaEliminar = true;
            } else {
                contador++;
            }
        }
        if (encontrarEnListaEliminar) {
            clients.remove(clients.get(contador));
        } else {
            System.err.println("No existe dicho cliente, por tanto no se puede eliminar");
        }
    }
}
