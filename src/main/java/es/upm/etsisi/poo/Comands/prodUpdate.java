package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;

public class prodUpdate extends Product{
    /**
     * Permite modificar un atributo de un producto.
     */
    public void apply(String id, String change, String value, Catalog catalog) {
        catalog.update(id,change,value);
    }
}
