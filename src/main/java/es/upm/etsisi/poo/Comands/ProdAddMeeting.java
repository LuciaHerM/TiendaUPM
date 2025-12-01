package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TypeEvent;

public class ProdAddMeeting extends Product {
    private String id;
    private String name;
    private String price;
    private String expiration_day;
    private String num_person;
    private Catalog catalog;

    // CONSTRUCTOR con todos los parámetros
    public ProdAddMeeting(String id, String name, String price, String expiration_day, String num_person, Catalog catalog) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expiration_day = expiration_day;
        this.num_person = num_person;
        this.catalog = catalog;
    }

    // CONSTRUCTOR sin ID
    public ProdAddMeeting(String name, String price, String expiration_day, String num_person, Catalog catalog) {
        this.id = catalog.createId();
        this.name = name;
        this.price = price;
        this.expiration_day = expiration_day;
        this.num_person = num_person;
        this.catalog = catalog;
    }
    /**
     * Añade un producto de tipo reunion llamando al metodo dentro de catalogo
     */
    public void apply() {
        catalog.addEvent(id, name,price,expiration_day,num_person, TypeEvent.MEETING);
    }

}
