package es.upm.etsisi.poo;

import java.util.Optional;

public class Product {
    public String id;
    public String name;
    public Category category;
    public Double price;
    public int max_pers=0;
    public String[] personalizaciones;
    public Product(String id, String name, Category category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.personalizaciones=null;
    }
    public Product(String id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category=null;
        this.personalizaciones=null;
    }

    public Product(String id, String name, Category category, Double price,int max_pers) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.personalizaciones=null;
        if(max_pers>0){
            this.max_pers=max_pers;
        }else {
            System.out.println("It's not possible to insert that maximon personalization. ");
        }
    }

    public Product() {
    }

    public String[] getPersonalizaciones() {
        return personalizaciones;
    }

    public void setPersonalizaciones(String[] personalizaciones) {
        if(max_pers > 0){
            if(personalizaciones.length<=max_pers){
                this.personalizaciones = personalizaciones;
                this.price += this.price*0.1* personalizaciones.length;
            }
            else {
                System.out.println("no puedes introducir esa cantidad de personalizaciones .");
            }
        }
        else {
            System.out.println("no puedes introducir personalizaciones a un producto no personalizable .");
        }
    }

    public String getID() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        if(personalizaciones==null) {
            return "{class:Product, id:" + id + ", name:'" + name + "', category:" + category + ", price:" + price + "}";
        }
        else {
            return "{class:Product, id:" + id + ", name:'" + name + "', category:" + category + ", price:" + price + ", maxPersonal: " + max_pers + ", personalizationList:[" + personalizacionesToString()+ "]}";
        }
    }
    @Override
    public Product clone() {
        Product copia = new Product();
        copia.id = this.id;
        copia.name = this.name;
        copia.category = this.category;
        copia.price = this.price;
        copia.max_pers = this.max_pers;
        if (this.personalizaciones != null) {
            copia.personalizaciones = new String[this.personalizaciones.length];
            for (int i = 0; i < this.personalizaciones.length; i++) {
                copia.personalizaciones[i] = this.personalizaciones[i];
            }
        } else {
            copia.personalizaciones = null;
        }

        return copia;
    }
    public String personalizacionesToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personalizaciones.length; i++) {
            sb.append(personalizaciones[i]);
            if (i < personalizaciones.length - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

}
