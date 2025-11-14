package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;

public class prodAddPer extends Product {
    private String id;
    private String name;
    private String category;
    private String price;
    private String pers;
    private String catalog;

    // Constructor
    public void ProdAddPer(String id, String name, String category, String price, String pers, Catalog catalog) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.pers = pers;
    }
    public void ProdAddPer(String name, String category, String price, String pers , Catalog catalog) {
        this.id = catalog.crearId();
        this.name = name;
        this.category = category;
        this.price = price;
        this.pers = pers;
    }
    public void apply(){
    }


}

