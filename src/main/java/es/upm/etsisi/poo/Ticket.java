package es.upm.etsisi.poo;

public class Ticket {
    private Product[] cart;
    Double totalPrice;
    private static int MAX_CART_PRODUCTS = 100;

    public Ticket(){
        this.cart = new Product[MAX_CART_PRODUCTS];
        this.totalPrice = 0.0;
    }
    public void AddProduct(Product producto){

    }
    public void RemoveProduct(Product producto){

    }
    public void RemoveAll(){

    }
    public String ToString(){
     return null;
    }
}
