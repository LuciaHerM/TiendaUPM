package es.upm.etsisi.poo.Comands;

public class UnknownComand extends Comands{

    @Override
    public void apply(){
        System.out.println("Command not found");
    }
}
