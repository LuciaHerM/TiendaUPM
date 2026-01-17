package es.upm.etsisi.poo;
import es.upm.etsisi.poo.persistence.ProductDAO;

import java.util.Arrays;
import java.util.ArrayList;
import es.upm.etsisi.poo.TiendaUPMExcepcion;



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
            if(p.getID().contains("S")){
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
    public void add(String id, String name, String category, String price) throws TiendaUPMExcepcion {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< num_products){
            if(products[i].getID().equals(id)) {
                encontrado=true;
                throw new TiendaUPMExcepcion("Can't be added a product with the same id", "ERR_PRODCUTID"){
                };
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
                    default -> throw new TiendaUPMExcepcion("Unexpected value: " + category, "ERR_UNEXPECTED");
                };
                double price1 = Double.parseDouble(price);
                Product_Basic product = new Product_Basic(id, name, category1, price1);
                products[num_products] = product;
                num_products++;
                ProductDAO.save(product);
                System.out.println(product.toString());
                Notifier.showSuccessAddProduct();
            }
        } else{
            throw new TiendaUPMExcepcion("The product can't be added.", "ERR_PRODUCT");
        }
    }
    public void addPer(String id, String name, String category, String price, String pers) throws TiendaUPMExcepcion {
        int i = 0;
        boolean encontrado = false;
        while (!encontrado && i < num_products) {
            if (products[i].getID().equals(id)) {
                encontrado = true;
                throw new TiendaUPMExcepcion("Can't be added a product with the same id", "ERR_PRODCUTID");
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
                    default -> throw new TiendaUPMExcepcion("Unexpected value: " + category, "ERR_UNEXPECTED");
                };
                double price1 = Double.parseDouble(price);
                int pers1 = Integer.parseInt(pers);
                Personalized product = new Personalized(id, name, category1, price1, pers1);
                products[num_products] = product;
                num_products++;
                ProductDAO.save(product);
                System.out.println(product.toString());
                Notifier.showSuccessAddProduct();

            }
        } else{
            throw new TiendaUPMExcepcion("The product can't be added.", "ERR_PRODUCT");
        }
    }

    public void addEvent(String id, String name, String price, String expiration_day, String num_person, TypeEvent typeEvent) throws TiendaUPMExcepcion {
        int i=0;
        boolean encontrado=false;
        while(!encontrado && i< num_products){
            if(products[i].getID().equals(id)) {
                encontrado=true;
                throw new TiendaUPMExcepcion("Can't be added a product with the same id", "ERR_PRODCUTID"){
                };
            }
            i++;
        }
        if(id!=null && name!=null && typeEvent!=null && price!=null){
            if (!encontrado && num_products < MAX_NUM_PRODUCTS) {
                double priceDouble = Double.parseDouble(price);
                int num_personInt = Integer.parseInt(num_person);
                Events productEvent = Events.createFromInput(id, name, priceDouble, expiration_day, num_personInt, typeEvent);
                if(productEvent != null) {
                    String mensajeUsuario;
                    if (typeEvent.equals(TypeEvent.MEETING)) {
                        mensajeUsuario = "Error processing -> prod addMeeting -> Error adding product";
                    } else if (typeEvent.equals(TypeEvent.FOOD)) {
                        mensajeUsuario = "Error processing -> prod addFood -> Error adding product";
                    } else {
                        mensajeUsuario = "Error processing -> Error adding product";
                    }
                    throw new TiendaUPMExcepcion(mensajeUsuario, "ERR_ADD_EVENT");
                }else{
                    products[num_products] = productEvent;
                    num_products++;
                    ProductDAO.save(productEvent);
                    System.out.println(productEvent.toString());
                    Notifier.showSuccessAddEvent(typeEvent);
                }
            }
        } else {
            throw new TiendaUPMExcepcion("The product can't be added.", "ERR_PRODUCT");
        }
    }
    public void addService(String expiration_day, String category)throws TiendaUPMExcepcion{
        if(expiration_day!=null && category!=null){
            Category_service category_service = switch (category){
                case "TRANSPORT" -> Category_service.TRANSPORT;
                case "SHOW" -> Category_service.SHOW;
                case "INSURANCE" -> Category_service.INSURANCE;
                default -> throw new TiendaUPMExcepcion("Unexpected value: " + category, "ERR_UNEXPECTEDVALUED");
            };
            if(num_products<MAX_NUM_PRODUCTS ){
                Services services= Services.createFromInput(expiration_day,category_service,createID_S());
                if(services.getExpiration_day()!=null){
                    products[num_products]=services;
                    num_products++;
                    num_sesiones++;
                    ProductDAO.save(services);
                    System.out.println(services.toString());
                    Notifier.showSuccessAddProduct();
                }
            }else{
                throw new TiendaUPMExcepcion("The catalog is full, you can't add more products.", "ERR_CATALOG");
            }
        }else{
            throw new TiendaUPMExcepcion("The product can't be added.",  "ERR_PRODUCT");
        }
    }

    /**
     * Muestra el catálogo de productos actualmente registrados.
     */
    public void list() {
        Product[] copia = new Product[num_products];
        for (int i = 0; i < num_products; i++) {
            copia[i] = products[i];
        }
        Arrays.sort(copia,(p1,p2)-> p1.getID().compareTo(p2.getID()));

        System.out.println("Catalog:");
        for(int i=0;i<num_products;i++){
            System.out.println(" "+copia[i].toString());
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
    public void update(String id, String change, String value) throws TiendaUPMExcepcion {
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
                            default -> throw new TiendaUPMExcepcion("Unexpected value: " + value, "ERR_UNEXPECTEDVALUE");
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
                            default -> throw new TiendaUPMExcepcion("Unexpected value: " + value, "ERR_UNEXPECTEDVALUE");
                        };
                        product.setCategory(category);
                        products[i]=product;
                    }else if(products[i].getClass().equals(Services.class)){
                        Services product = (Services) products[i];
                        Category_service category = switch (value){
                            case "TRANSPORT" -> Category_service.TRANSPORT;
                            case "SHOWS" -> Category_service.SHOW;
                            case "INSURANCE" -> Category_service.INSURANCE;
                            default -> throw new TiendaUPMExcepcion("Unexpected value: " + value, "ERR_UNEXPECTEDVALUE");
                        };
                        product.setCategory_service(category);
                        products[i]=product;
                    } else if (products[i].getClass().equals(Events.class)) throw new TiendaUPMExcepcion("Can't be edit de category or typeEvent of a Event", "ERR_CATEGORY"){
                    };
                    break;
                case "PRICE":
                    double price = Double.parseDouble(value);
                    products[i].setPrice(price);
                    break;
            }
            ProductDAO.update(id,change,value);
            System.out.println(products[i].toString());
            System.out.println("prod update: ok");
        }else {
            throw new TiendaUPMExcepcion("Product to update not found", "ERR_PRODUCT");
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
            ProductDAO.delete(products[i].getID());
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

        String sol="0";
        if(products[0]!=null) {
            int idBuscado=0;
            boolean find=false;
            while (idBuscado<=num_products && find){
                boolean idEncontrado = false;
                int i=0;
                while(i<num_products && !idEncontrado){
                    if (products[i] != null && products[i].getID() != null) {
                        int idActual = Integer.parseInt(products[i].getID());
                        if (idActual == idBuscado) {
                            idEncontrado = true;
                        }
                    }
                    i++;
                }
                if (!idEncontrado) {
                    find=true;
                    sol= Integer.toString(idBuscado);
                }
                idBuscado++;
            }
        }
        return sol;
    }

    public String createID_S(){
        int num_id=num_sesiones+1;
        String ID_S;
        ID_S = String.valueOf(num_id) + "S";
        return ID_S;
    }
}
