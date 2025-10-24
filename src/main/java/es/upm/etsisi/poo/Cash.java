package es.upm.etsisi.poo;

public class Cash extends User{

    private static String id;

    public static void Cash (String id, String name, String email){
        Cash.id=id;
        Cash.name=name;
        Cash.email=email;
    }

    public static String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{class:Cash, id:"+id+", name:'"+name+"', email:"+ email+"}";    }
}
