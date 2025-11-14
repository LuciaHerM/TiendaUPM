package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TypeEvent;

public class prodAddFood extends Product{
    /**
     * Añade un producto de tipo food llamando al metodo dentro de catalogo
     * @param id es el id del producto
     * @param name es el nombre del producto
     * @param price es el precio del producto
     * @param expiration_day es el dia que se quiere la comida
     * @param num_person es el numero de personas para las que se quire la comida
     */
    public void apply(String id, String name, String price, String expiration_day, String num_person, Catalog catalog) {
        catalog.addEvent(id, name,price,expiration_day,num_person, TypeEvent.FOOD);
    }
    public void apply(String name, String price, String expiration_day, String num_person , Catalog catalog) {
        String id=catalog.crearId();
        catalog.addEvent(id, name,price,expiration_day,num_person,TypeEvent.FOOD);
    }
}
