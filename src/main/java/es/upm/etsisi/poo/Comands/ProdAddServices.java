package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TiendaUPMExcepcion;

public class ProdAddServices extends Product {
    private String expiration_day;
    private String category_service;
    private Catalog catalog;

    public ProdAddServices(String expiration_day, String category_service, Catalog catalog) {
        this.expiration_day = expiration_day;
        this.category_service = category_service;
        this.catalog = catalog;
    }

    public void apply() throws TiendaUPMExcepcion {
        catalog.addService(expiration_day,category_service);
    }
}
