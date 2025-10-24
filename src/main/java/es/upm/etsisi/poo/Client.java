package es.upm.etsisi.poo;

public class Client extends User{

    private static String DNI;
    private static String cashId;

    public static void Client (String name, String DNI, String email, String cashId){
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
}
