package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TiendaUPMExcepcion;
import es.upm.etsisi.poo.TypeEvent;

public class ProdAddFood extends Product{
    private String id;
    private String name;
    private String price;
    private String expiration_day;
    private String num_person;
    private Catalog catalog;

    // Constructor con todos los parámetros
    public ProdAddFood(String id, String name, String price, String expiration_day, String num_person, Catalog catalog) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expiration_day = expiration_day;
        this.num_person = num_person;
        this.catalog = catalog;
    }
    public ProdAddFood(String name, String price, String expiration_day, String num_person, Catalog catalog) {
        this.id = catalog.createId();
        this.name = name;
        this.price = price;
        this.expiration_day = expiration_day;
        this.num_person = num_person;
        this.catalog = catalog;
    }
    /**
     * Añade un producto de tipo food llamando al metodo dentro de catalogo
     */
    public void apply() throws TiendaUPMExcepcion {
        catalog.addEvent(id, name,price,expiration_day,num_person, TypeEvent.FOOD);
    }
}
