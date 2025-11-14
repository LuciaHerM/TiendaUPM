package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;

public class prodAdd extends Product {
    /**
     * Añade un nuevo producto al catálogo de la tienda.
     * @param id
     * @param name
     * @param category
     * @param price
     * @param catalog
     */
    public void apply(String id, String name, String category, String price , Catalog catalog){
        catalog.add(id,name,category,price);
    }
    /**
     * Añade un nuevo producto al catálogo de la tienda.
     */
    public void apply(String name, String category, String price , Catalog catalog) {
        String id=catalog.crearId();
        catalog.add(id,name,category,price);
    }
}