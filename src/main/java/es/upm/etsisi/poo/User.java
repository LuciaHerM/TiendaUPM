package es.upm.etsisi.poo;

public abstract class User {

    public static String name;
    public static String email;

    public static void User(String name, String email){
        User.name=name;
        User.email=email;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

}
