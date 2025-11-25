package es.upm.etsisi.poo;

public abstract class User {

    public String name;
    public String email;

    public User(String name, String email){
        this.name=name;
        this.email=email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
