package es.upm.etsisi.poo.Comands;

import es.upm.etsisi.poo.Catalog;
import es.upm.etsisi.poo.TypeEvent;

public class prodAddMeeting extends Product {
    /**
     * Añade un producto de tipo reunion llamando al metodo dentro de catalogo
     * @param id es el id del producto
     * @param name es el nombre del producto
     * @param price es el precio del producto
     * @param expiration_day es el dia que se quiere la reunion
     * @param num_person es el numero de personas que van a acceder a la reunion
     */
    public void apply(String id, String name, String price, String expiration_day, String num_person, Catalog catalog) {
        catalog.addEvent(id, name,price,expiration_day,num_person, TypeEvent.MEETING);
    }
    public void apply(String name, String price, String expiration_day, String num_person,Catalog catalog) {
        String id=catalog.crearId();
        catalog.addEvent(id, name,price,expiration_day,num_person,TypeEvent.MEETING);
    }
}
