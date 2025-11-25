package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;

public class ProdRemove extends Product{
    private String id;
    private Catalog catalog;
    public ProdRemove(String id, Catalog catalog) {
        this.id = id;
        this.catalog = catalog;
    }
    /**
     * Elimina un producto del catálogo.
     */
    public void apply() {
        catalog.remove(id);
    }
}
