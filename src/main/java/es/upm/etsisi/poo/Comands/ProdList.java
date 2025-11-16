package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;

public class ProdList extends Product{
    private Catalog catalog;
    public ProdList(Catalog catalog){
        this.catalog=catalog;
    }
    /**
     * Muestra el catálogo de productos actualmente registrados.
     */
    public void apply(Catalog catalog) {
        catalog.list();
    }
}
