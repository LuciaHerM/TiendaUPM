package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.persistence.DatabaseManager;

public class ComandDeleteAll extends Comands {

    public void apply(){
        DatabaseManager.deleteAll();
        System.out.println("Base de datos borrada.");
    }
}
