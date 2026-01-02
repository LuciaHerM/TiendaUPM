package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Client;

public class NormalClient extends Client {
    private String DNI;

    public NormalClient(String name, String DNI, String email, String id){
        super(name, email, id);
        this.DNI=DNI;
    }

    public String getIdentifier(){
        return DNI;
    }

    @Override
    public boolean isCompany() {
        return false;
    }
}
