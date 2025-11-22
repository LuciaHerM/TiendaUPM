package es.upm.etsisi.poo;

import java.util.Arrays;

public class Catalog {

    private static Product[] products;
    private static final int MAX_NUM_PRODUCTS = 200;
    private static int num_products;

    public Catalog() {
        products = new Product[MAX_NUM_PRODUCTS];
        num_products=0;
    }

    public int length(){
        return products.length;
    }

    public Product find(int id){
        return products[id];
    }

    /**
     * Añade un nuevo producto al catalogo de la tienda.
     *
     * @param id   Identificador único del producto.
     * @param name  Nombre del producto.
     * @param category  Categoria del producto perteneciente al enum Category.
     * @param price Precio del producto.
     */
    public void add(String id, String name, String category, String price) {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< num_products){
            if(products[i].getID().equals(id)){
                System.out.println("Can't be add a product with the same id");
                encontrado=true;
            }
            i++;
        }
        if(id!=null && name!=null && category!=null && price!=null) {
            if (!encontrado && num_products < MAX_NUM_PRODUCTS) {
                Category category1 = switch (category) {
                    case "MERCH" -> Category.MERCH;
                    case "STATIONERY" -> Category.STATIONERY;
                    case "CLOTHES" -> Category.CLOTHES;
                    case "BOOK" -> Category.BOOK;
                    case "ELECTRONICS" -> Category.ELECTRONICS;
                    default -> throw new IllegalStateException("Unexpected value: " + category);
                };
                double price1 = Double.parseDouble(price);
                Product product = new Product(id, name, category1, price1);
                products[num_products] = product;
                num_products++;
                System.out.println(product.toString());
                System.out.println("prod add: ok");
            }
        } else {
            System.err.println("The product can't be add.");
        }
    }
    public void addPer(String id, String name, String category, String price, String pers) {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< num_products){
            if(products[i].getID().equals(id)){
                System.out.println("Can't be add a product with the same id");
                encontrado=true;
            }
            i++;
        }
        if(id!=null && name!=null && category!=null && price!=null) {
            if (!encontrado && num_products < MAX_NUM_PRODUCTS) {
                Category category1 = switch (category) {
                    case "MERCH" -> Category.MERCH;
                    case "STATIONERY" -> Category.STATIONERY;
                    case "CLOTHES" -> Category.CLOTHES;
                    case "BOOK" -> Category.BOOK;
                    case "ELECTRONICS" -> Category.ELECTRONICS;
                    default -> throw new IllegalStateException("Unexpected value: " + category);
                };
                double price1 = Double.parseDouble(price);
                int pers1 = Integer.parseInt(pers);
                Product product = new Product(id, name, category1, price1, pers1);
                products[num_products] = product;
                num_products++;
                System.out.println(product.toString());
                System.out.println("prod add: ok");
            }
        } else {
            System.err.println("The product can't be add.");
        }
    }

    public void addEvent(String id, String name, String price, String expiration_day, String num_person, TypeEvent typeEvent){
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< num_products){
            if(products[i].getID().equals(id)){
                System.out.println("Can't be add a product with the same id");
                encontrado=true;
            }
            i++;
        }
        if(id!=null && name!=null && typeEvent!=null && price!=null){
            if(Events.check_min_time(expiration_day, typeEvent)) {
                if (!encontrado && num_products < MAX_NUM_PRODUCTS) {
                    double priceDouble = Double.parseDouble(price);
                    int num_personInt = Integer.parseInt(num_person);
                    Events productEvent = new Events(id, name, priceDouble, expiration_day, num_personInt, typeEvent);
                    products[num_products] = productEvent;
                    num_products++;
                    System.out.println(productEvent.toString());
                    System.out.println("prod add: ok");
                }
            } else {
                System.err.println("The product can't be add.");
            }
        } else {
            System.err.println("The product can't be add.");
        }
    }


    /**
     * Muestra el catálogo de productos actualmente registrados.
     */
    public void list() {
        System.out.println("Catalog:");
        for(int i=0;i<num_products;i++){
            System.out.println(" "+products[i].toString());
        }
        System.out.println("prod list: ok");
    }


    /**
     * Permite modificar un atributo de un producto.
     *
     * @param id    Identificador del producto.
     * @param change    Atributo a modificar (name, category o price).
     * @param value     Nuevo valor del atributo.
     */
    public void update(String id, String change, String value) {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i<num_products){
            if(products[i].getID().equals(id)){
                encontrado=true;
            }
            else
                i++;
        }
        if(encontrado){
            switch (change){
                case "NAME":
                    products[i].setName(value);
                    break;
                case "CATEGORY":
                    Category category = switch (value){
                        case "MERCH" -> Category.MERCH;
                        case "STATIONERY"-> Category.STATIONERY;
                        case "CLOTHES" -> Category.CLOTHES;
                        case "BOOK" -> Category.BOOK;
                        case "ELECTRONICS" -> Category.ELECTRONICS;
                        default -> throw new IllegalStateException("Unexpected value: " + value);
                    };
                    products[i].setCategory(category);
                    break;
                case "PRICE":
                    double price = Double.parseDouble(value);
                    products[i].setPrice(price);
                    break;
            }
            System.out.println(products[i].toString());
            System.out.println("prod update: ok");
        }else{
            System.out.println("Product to update not found");
        }
    }

    /**
     * Elimina un producto del catálogo.
     * @param id    Identificador del producto.
     */
    public void remove(String id) {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< products.length){
            if(products[i].getID().equals(id)){
                encontrado=true;
            }
            else
                i++;
        }
        if(encontrado){
            for(int j=i;j<num_products;j++){
                products[j]=products[j+1];
            }
            num_products--;
            System.out.println("prod remove: ok");
        }else{
            System.out.println("Product to remove not found");
        }
    }

    public String crearId(){
        int antId=0;
        if(products[0]!=null) {
            antId = Integer.parseInt(products[0].getID());
            for (int i = 1; i < num_products; i++) {
                int id = Integer.parseInt(products[i].getID());
                if (antId < id) {
                    antId = id;
                }
            }
        }
        return Integer.toString(antId+1);
    }
}
