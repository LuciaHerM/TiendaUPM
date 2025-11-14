package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TypeEvent;

public class prodAddFood extends Product{
    private String id;
    private String name;
    private String price;
    private String expiration_day;
    private String num_person;
    private Catalog catalog;

    // Constructor con todos los parámetros
    public prodAddFood(String id, String name, String price, String expiration_day, String num_person, Catalog catalog) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expiration_day = expiration_day;
        this.num_person = num_person;
        this.catalog = catalog;
    }
    public prodAddFood(String name, String price, String expiration_day, String num_person, Catalog catalog) {
        this.id = catalog.crearId();
        this.name = name;
        this.price = price;
        this.expiration_day = expiration_day;
        this.num_person = num_person;
        this.catalog = catalog;
    }
    /**
     * Añade un producto de tipo food llamando al metodo dentro de catalogo
     */
    public void apply() {
        catalog.addEvent(id, name,price,expiration_day,num_person, TypeEvent.FOOD);
    }
}
