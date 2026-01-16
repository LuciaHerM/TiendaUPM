package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Client;
import es.upm.etsisi.poo.TiendaUPMExcepcion;
import es.upm.etsisi.poo.persistence.ClientDAO;

import java.util.ArrayList;

public class RemoveClient extends ComandClient {
    private String DNI;
    private ArrayList<Client> clients;
    public RemoveClient(String DNI, ArrayList<Client> clients) {
        this.DNI = DNI;
        this.clients = clients;
    }

    public void apply() throws TiendaUPMExcepcion {
        boolean encontrarEnListaEliminar = false;
        int contador = 0;
        while (!encontrarEnListaEliminar && contador < clients.size()) {
            if (clients.get(contador).getId().equals(DNI)) {
                encontrarEnListaEliminar = true;
            } else {
                contador++;
            }
        }
        if (encontrarEnListaEliminar) {
            String id = clients.get(contador).getId();
            clients.remove(clients.get(contador));
            ClientDAO.delete(id);
            System.out.println("client remove: ok");
        } else {
            throw new TiendaUPMExcepcion("That client does'nt exist, so it can't be remove", "ERR_CLIENT");
        }
    }
}
