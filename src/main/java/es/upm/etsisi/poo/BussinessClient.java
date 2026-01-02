package es.upm.etsisi.poo;

public class BussinessClient extends Client{

    public String NIF;

    public BussinessClient(String name, String NIF, String email, String cashId){
        super(name, NIF, email, cashId);
        this.NIF = NIF;
    }

    public String getNIF() {
        return NIF;
    }
}
