package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TiendaUPMExcepcion;

public class ProdAddPer extends Product {
    private String id;
    private String name;
    private String category;
    private String price;
    private String pers;
    private Catalog catalog;

    // Constructor
    public ProdAddPer(String id, String name, String category, String price, String pers, Catalog catalog) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.pers = pers;
        this.catalog=catalog;
    }
    public ProdAddPer(String name, String category, String price, String pers , Catalog catalog) {
        this.id = catalog.createId();
        this.name = name;
        this.category = category;
        this.price = price;
        this.pers = pers;
        this.catalog=catalog;
    }
    public void apply() throws TiendaUPMExcepcion {
        catalog.addPer(id,name,category,price,pers);
    }


}

