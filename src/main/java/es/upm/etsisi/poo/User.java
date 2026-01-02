package es.upm.etsisi.poo;

public abstract class User {

    public String name;
    public String email;
    public String id;

    public User(String name, String email, String id){
        this.name=name;
        this.email=email;
        this.id = id;
    }

    public String getId() {return id;}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
