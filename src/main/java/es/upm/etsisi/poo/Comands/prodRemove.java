package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;

public class prodRemove extends Product{
    /**
     * Elimina un producto del catálogo.
     */
    public void apply(String id, Catalog catalog) {
        catalog.remove(id);
    }
}
