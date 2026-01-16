package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TiendaUPMExcepcion;

public class ProdAdd extends Product {
    private String id;
    private String name;
    private String category;
    private String price;
    private Catalog catalog;

    // Constructor
    public ProdAdd(String id, String name, String category, String price, Catalog catalog) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.catalog = catalog;
    }
    public ProdAdd(String name, String category, String price, Catalog catalog) {
        this.id = catalog.createId();
        this.name = name;
        this.category = category;
        this.price = price;
        this.catalog = catalog;
    }
    /**
     * Añade un nuevo producto al catálogo de la tienda.

     */
    public void apply() throws TiendaUPMExcepcion {
        catalog.add(id,name,category,price);
    }
}