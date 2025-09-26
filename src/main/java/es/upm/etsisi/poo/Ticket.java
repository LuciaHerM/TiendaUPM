package es.upm.etsisi.poo;

public class Ticket {
    private Product[] cart;
    private Double totalPrice;
    private static int MAX_CART_PRODUCTS = 100;
    private int productNumber;
    private Double totalDiscount;

    public Ticket(){
        this.cart = new Product[MAX_CART_PRODUCTS];
        this.totalPrice = 0.0;
        this.productNumber = 0;
    }
    public void AddProduct(Product producto){
        cart[productNumber] = producto;
        productNumber++;
    }
    public void RemoveProduct(Product producto){

    }
    public void RemoveAll(){
        for (int i = productNumber - 1; i > 0; i--) {
            RemoveProduct(cart[i]);
        }
    }
    public String ToString(){
     return null;
    }
}
