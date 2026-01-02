package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Client;

public class BussinessClient extends Client {
    private String NIF;

    public BussinessClient(String name, String NIF, String email, String id) {
        super(name, email, id);
        this.NIF = NIF;
    }

    public String getIdentifier() {
        return NIF;
    }

    @Override
    public boolean isCompany() {
        return true;
    }
}
