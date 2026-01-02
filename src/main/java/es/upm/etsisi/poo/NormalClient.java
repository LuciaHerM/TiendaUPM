package es.upm.etsisi.poo;

public class NormalClient extends Client{

    public String DNI;

    public NormalClient(String name, String DNI, String email, String cashId){
        super(name, DNI, email, cashId);
        this.DNI = DNI;
    }

    public String getDNI() {
        return DNI;
    }
}
