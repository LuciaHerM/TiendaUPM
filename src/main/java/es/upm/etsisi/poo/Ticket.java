package es.upm.etsisi.poo;

public class Ticket {
    private Product[] cart;
    private Double totalPrice;
    private static int MAX_CART_PRODUCTS = 100;
    private int productNumber;
    private Double totalDiscount;

//Constructor de la clase ticket, que asigna valores a las variables privadas.
    public Ticket(){
        this.cart = new Product[MAX_CART_PRODUCTS];
        this.totalPrice = 0.0;
        this.productNumber = 0;
        this.totalDiscount = 0.0;
    }
//Añade el producto pasado por parámetro en la posición del total de productos
//(debido a que empiezan los arrays en 0)
    public void AddProduct(Product producto) {
        cart[productNumber] = producto;
        productNumber++;
    }
//Elimina el producto pasado por parámetro.
//Recoloca los productos por si queda un hueco en el array
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
//Utiliza un bucle para eliminar los productos desde
// el último producto hasta la posición 0.
    public void RemoveAll(){
        for (int i = productNumber - 1; i > 0; i--) {
            cart[i] = null;
        }
    }
    public String ToString(){
     return null;
    }
}
