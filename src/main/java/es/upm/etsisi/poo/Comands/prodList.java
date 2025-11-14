package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;

public class prodList extends Product{
    private Catalog catalog;
    public void prodList(Catalog catalog){
        this.catalog=catalog;
    }
    /**
     * Muestra el catálogo de productos actualmente registrados.
     */
    public void apply(Catalog catalog) {
        catalog.list();
    }
}
