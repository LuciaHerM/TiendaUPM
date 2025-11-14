package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;

public class prodAdd extends Product {
    private String id;
    private String name;
    private String category;
    private String price;
    private Catalog catalog;

    // Constructor
    public void Product(String id, String name, String category, String price, Catalog catalog) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.catalog = catalog;
    }
    public void Product(String name, String category, String price, Catalog catalog) {
        this.id = catalog.crearId();
        this.name = name;
        this.category = category;
        this.price = price;
        this.catalog = catalog;
    }
    /**
     * Añade un nuevo producto al catálogo de la tienda.

     */
    public void apply(){
        catalog.add(id,name,category,price);
    }
}