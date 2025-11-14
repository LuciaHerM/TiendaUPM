package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;

public class prodRemove extends Product{
    private String id;
    private Catalog catalog;
    public prodRemove(String id, Catalog catalog) {
        this.id = id;
        this.catalog = catalog;
    }
    /**
     * Elimina un producto del catálogo.
     */
    public void apply(String id, Catalog catalog) {
        catalog.remove(id);
    }
}
