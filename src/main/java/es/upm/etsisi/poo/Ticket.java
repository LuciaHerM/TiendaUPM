package es.upm.etsisi.poo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * La clase Ticket representa un ticket de compra en la app. Permite gestionar los productos, los descuentos e
 * imprimir el ticket a traves de sus metodos.
 */
public class Ticket {
    private Product[] cart;
    private Double totalPrice;
    private static final int MAX_CART_PRODUCTS = 100;
    private int productNumber;
    private Double totalDiscount;

    /**
     * Constructor de la clase Ticket, que inicializa las variables privadas.
     */
    public Ticket(){
        this.cart = new Product[MAX_CART_PRODUCTS];
        this.totalPrice = 0.0;
        this.productNumber = 0;
        this.totalDiscount = 0.0;
    }

    /**
     * Añade el producto al array de productos, cart. El producto se inserta en la siguiente posición libre del array.
     * @param producto  Producto a añadir al ticket.
     */
    public void AddProduct(Product producto) {
        if(productNumber<MAX_CART_PRODUCTS) {
            cart[productNumber] = producto;
            productNumber++;
            Arrays.sort(cart,Comparator.comparing(Product::getName));
        }else{
            System.err.println("Intentas introducir más productos al Ticket.");
        }
    }

    /**
     * Elimina el producto de cart.
     * @param producto  Producto a eliminar del ticket.
     */
    public void RemoveProduct(Product producto){
        boolean find = false;
        for(int i = 0 ; i < productNumber ; i++) {
            if (cart[i].equals(producto) && !find) {
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

    /**
     * Permite eliminar todos los productos desde
     * el último producto hasta la posición 0.
    */
    public void RemoveAll(){
        for (int i = productNumber - 1; i > 0; i--) {
            cart[i] = null;
        }
    }

    /**
     * Crea una representacion textual del ticket con todos los productos, los descuentos aplicados y
     * el precio total. El metodo utiliza un for para contar el número de productos por categoria y poder aplicar el posible descuento.
     * @return  Cadena con el contenido del ticket.
     */
    public String ToString(){
        int numMerch=0;
        int numStationery=0;
        int numClothes=0;
        int numBook=0;
        int numElectronics=0;
        Arrays.sort(cart, 0, productNumber, (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < productNumber ; i++){
            switch (cart[i].getCategory()){
                case MERCH:
                    numMerch++;
                    break;
                case STATIONERY:
                    numStationery++;
                    break;
                case CLOTHES:
                    numClothes++;
                    break;
                case BOOK:
                    numBook++;
                    break;
                case ELECTRONICS:
                    numElectronics++;
                    break;
            }
        }
        for(int i = 0 ; i < productNumber ; i++){
            Double productDiscount=0.0;
            switch (cart[i].getCategory()){
                case MERCH:
                    if(numMerch>=2){
                        productDiscount=Category.MERCH.getDescuento();
                    }
                    break;
                case STATIONERY:
                    if(numStationery>=2){
                        productDiscount=Category.STATIONERY.getDescuento();
                    }
                    break;
                case CLOTHES:
                    if(numClothes>=2){
                        productDiscount=Category.CLOTHES.getDescuento();
                    }
                    break;
                case BOOK:
                    if(numBook>=2){
                        productDiscount=Category.BOOK.getDescuento();
                    }
                    break;
                case ELECTRONICS:
                    if(numElectronics>=2){
                        productDiscount=Category.ELECTRONICS.getDescuento();
                    }
                    break;
            }
            if(productDiscount==0.0){
                str.append(cart[i].toString() + " \n");
            }
            else {
                str.append(cart[i].toString() + " **discount -"+(cart[i].getPrice()*productDiscount)+"\n");
            }
            totalPrice+=cart[i].getPrice();
            totalDiscount+=cart[i].getPrice()*productDiscount;

        }
        str.append("Total price: "+totalPrice+"\n");
        str.append("Total discount: "+totalDiscount+"\n");
        str.append("Final Price: "+(totalPrice-totalDiscount)+"\n");
        str.append("ticket print: ok "+"\n");

        return str.toString();
    }
}
