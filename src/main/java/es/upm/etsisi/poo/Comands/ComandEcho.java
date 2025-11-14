package es.upm.etsisi.poo.Comands;

public class ComandEcho extends Comands{

    public void apply(String[] texto){
        System.out.println(texto[0] + " \"" + texto[1] + "\"");
    }
}
