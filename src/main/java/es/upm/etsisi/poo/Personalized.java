package es.upm.etsisi.poo;

import java.util.Arrays;

public class Personalized extends Product_Basic{
    public int max_pers=0;
    public String[] personalizaciones;

    public Personalized(String id, String name, Category category, Double price, int max_pers) {
        super(id,name,category,price);
        this.personalizaciones=null;
        if(max_pers>0){
            this.max_pers=max_pers;
        }else {
            Notifier.ErrorNumberPersonalization();
        }
    }

    public Personalized() {
        super();
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
                Notifier.ErrorNumberPersonalization();
            }
        }
        else {
            Notifier.CantPersonalize();
        }
    }

    public int getMax_pers() {
        return max_pers;
    }

    public String personalizacionesToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personalizaciones.length; i++) {
            sb.append(personalizaciones[i]);
            if (i < personalizaciones.length - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        if (personalizaciones==null){
            return " {class:ProductPersonalized, id:" + id + ", name:'" + name + "', category:" + category + ", price:" + price + ", maxPersonal:" + max_pers + "}";
        }
        else {
            return " {class:ProductPersonalized, id:" + id + ", name:'" + name + "', category:" + category + ", price:" + price + ", maxPersonal:" + max_pers + ", personalizationList:[" + personalizacionesToString()+ "]}";
        }
    }


    @Override
    public Personalized clone() {
        Personalized copia = new Personalized();
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
}
