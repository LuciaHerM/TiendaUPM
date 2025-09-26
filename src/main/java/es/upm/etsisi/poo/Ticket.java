package es.upm.etsisi.poo;

public class Ticket {
    private Product[] cart;
    Double totalPrice;
    private static int MAX_CART_PRODUCTS = 100;
    private int productNumber;

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
        boolean find = false;
        for(int i = 0 ; i < productNumber ; i++) {
            if (cart[i] == producto && !find) {
                find = true;
            }
            if (find) {
                cart[i] = cart[i + 1];
            }
        }
        if(find){
            productNumber--;
        }
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
