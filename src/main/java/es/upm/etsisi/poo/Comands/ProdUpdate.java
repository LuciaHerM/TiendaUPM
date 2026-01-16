package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TiendaUPMExcepcion;

public class ProdUpdate extends Product{
    private String id;
    private String change;
    private String value;
    private Catalog catalog;

    // Constructor
    public ProdUpdate(String id, String change, String value, Catalog catalog) {
        this.id = id;
        this.change = change;
        this.value = value;
        this.catalog = catalog;
    }
    /**
     * Permite modificar un atributo de un producto.
     */
    public void apply() throws TiendaUPMExcepcion {
        catalog.update(id,change,value);
    }
}
