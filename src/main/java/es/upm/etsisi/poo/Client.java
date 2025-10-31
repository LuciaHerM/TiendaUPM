package es.upm.etsisi.poo;

public class Client extends User{

    private static String DNI;
    private static String cashId;

    public Client (String name, String DNI, String email, String cashId){
        Client.name=name;
        Client.DNI=DNI;
        Client.email=email;
        Client.cashId=cashId;
    }

    public static String getDNI() {
        return DNI;
    }

    public static String getCashId() {
        return cashId;
    }

    @Override
    public String toString() {
        return "{class:Cliente, name:'"+name+"', DNI:"+DNI+", email:"+ email+", cashId:"+ cashId+"}";
    }
}
