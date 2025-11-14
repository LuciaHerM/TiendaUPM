package es.upm.etsisi.poo.Comands;

public class ComandEcho extends Comands{

    String[] texto;
    public ComandEcho(String[] s) {
        super();
        texto=s;
    }

    public void apply(){
        System.out.println(texto[0] + " \"" + texto[1] + "\"");
    }
}
