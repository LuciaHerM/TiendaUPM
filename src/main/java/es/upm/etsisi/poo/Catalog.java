package es.upm.etsisi.poo;

import java.util.ArrayList;

public class Catalog {

    private static Product[] products;
    private static final int MAX_NUM_PRODUCTS = 200;
    private static int num_products;
    private static int num_sesiones;

    public Catalog() {
        products = new Product[MAX_NUM_PRODUCTS];
        num_products=0;
        num_sesiones=0;
    }

    public Catalog(ArrayList<Product> all){
        products = new Product[MAX_NUM_PRODUCTS];
        num_products=0;
        num_sesiones=0;
        for(Product p : all){
            products[num_products]=p;
            num_products++;
            if(products[num_products].getID().contains("S")){
                num_sesiones++;
            }
        }
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
                Notifier.showErrorAddProductWithSameId();
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
                    default -> Notifier.UnexpectecValue(category);
                };
                double price1 = Double.parseDouble(price);
                Product_Basic product = new Product_Basic(id, name, category1, price1);
                products[num_products] = product;
                num_products++;
                System.out.println(product.toString());
                Notifier.showSuccessAddProduct();
            }
        } else {
            Notifier.showErrorAddProduct();
        }
    }
    public void addPer(String id, String name, String category, String price, String pers) {
        int i = 0;
        boolean encontrado = false;
        while (!encontrado && i < num_products) {
            if (products[i].getID().equals(id)) {
                Notifier.showErrorAddProductWithSameId();
                encontrado = true;
            }
            i++;
        }
        if (id != null && name != null && category != null && price != null) {
            if (!encontrado && num_products < MAX_NUM_PRODUCTS) {
                Category category1 = switch (category) {
                    case "MERCH" -> Category.MERCH;
                    case "STATIONERY" -> Category.STATIONERY;
                    case "CLOTHES" -> Category.CLOTHES;
                    case "BOOK" -> Category.BOOK;
                    case "ELECTRONICS" -> Category.ELECTRONICS;
                    default -> Notifier.UnexpectecValue(category);
                };
                double price1 = Double.parseDouble(price);
                int pers1 = Integer.parseInt(pers);
                Personalized product = new Personalized(id, name, category1, price1, pers1);
                products[num_products] = product;
                num_products++;
                System.out.println(product.toString());
                Notifier.showSuccessAddProduct();

            }
        } else {
            Notifier.showErrorAddProduct();
        }
    }

    public void addEvent(String id, String name, String price, String expiration_day, String num_person, TypeEvent typeEvent){
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< num_products){
            if(products[i].getID().equals(id)){
                Notifier.showErrorAddProductWithSameId();
                encontrado=true;
            }
            i++;
        }
        if(id!=null && name!=null && typeEvent!=null && price!=null){
            if (!encontrado && num_products < MAX_NUM_PRODUCTS) {
                double priceDouble = Double.parseDouble(price);
                int num_personInt = Integer.parseInt(num_person);
                Events productEvent = new Events(id, name, priceDouble, expiration_day, num_personInt, typeEvent);
                if(productEvent.getNum_person() == 0){
                    Notifier.showErrorAddEvent(typeEvent);
                }else{
                    products[num_products] = productEvent;
                    num_products++;
                    System.out.println(productEvent.toString());
                    Notifier.showSuccessAddEvent(typeEvent);
                }
            }
        } else {
            Notifier.showErrorAddProduct();
        }
    }
    public void addService(String expiration_day, String category){
        if(expiration_day!=null && category!=null){
            Category_service category_service = switch (category){
                case "TRANSPORT" -> Category_service.TRANSPORT;
                case "SHOW" -> Category_service.SHOW;
                case "INSURANCE" -> Category_service.INSURANCE;
                default -> Notifier.UnexpectecValueService(category);
            };
            if(num_products<MAX_NUM_PRODUCTS ){
                Services services= new Services(expiration_day,category_service,createID_S());
                if(services.getExpiration_day()!=null){
                    products[num_products]=services;
                    num_products++;
                    num_sesiones++;
                    Notifier.showSuccessAddService(category_service);
                }
            }else{
                Notifier.fullCatalog();
            }
        }else{
            Notifier.showErrorAddProduct();
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
    public void update(String id, String change, String value) throws IllegalStateException {
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
                    if(products[i].getClass().equals(Product_Basic.class)){
                        Product_Basic product = (Product_Basic) products[i];
                        Category category = switch (value) {
                            case "MERCH" -> Category.MERCH;
                            case "STATIONERY" -> Category.STATIONERY;
                            case "CLOTHES" -> Category.CLOTHES;
                            case "BOOK" -> Category.BOOK;
                            case "ELECTRONICS" -> Category.ELECTRONICS;
                            default -> Notifier.UnexpectecValue(value);
                        };
                        product.setCategory(category);
                        products[i] = product;

                    } else if (products[i].getClass().equals(Personalized.class)) {
                        Personalized product = (Personalized) products[i];
                        Category category = switch (value){
                            case "MERCH" -> Category.MERCH;
                            case "STATIONERY"-> Category.STATIONERY;
                            case "CLOTHES" -> Category.CLOTHES;
                            case "BOOK" -> Category.BOOK;
                            case "ELECTRONICS" -> Category.ELECTRONICS;
                            default -> Notifier.UnexpectecValue(value);
                        };
                        product.setCategory(category);
                        products[i]=product;
                    }else if(products[i].getClass().equals(Services.class)){
                        Services product = (Services) products[i];
                        Category_service category = switch (value){
                            case "TRANSPORT" -> Category_service.TRANSPORT;
                            case "SHOWS" -> Category_service.SHOW;
                            case "INSURANCE" -> Category_service.INSURANCE;
                            default -> Notifier.UnexpectecValueService(value);
                        };
                        product.setCategory_service(category);
                        products[i]=product;
                    } else if (products[i].getClass().equals(Events.class)) {
                        System.out.println("Can't be edit de category or typeEvent of a Event");
                    }
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
            System.out.println(products[i].toString());
            Product p_aux=null;
            for(int j=i;j<num_products;j++){
                p_aux=products[j];
                products[j]=products[j+1];
            }
            num_products--;
            if(p_aux.getID().contains("S")&& p_aux!=null){
                num_sesiones--;
            }
            System.out.println("prod remove: ok");
        }else{
            System.out.println("Product to remove not found");
        }
    }

    public String createId(){
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
    public String createID_S(){
        int num_id=num_sesiones+1;
        String ID_S;
        ID_S = String.valueOf(num_id) + "S";
        return ID_S;
    }
}
