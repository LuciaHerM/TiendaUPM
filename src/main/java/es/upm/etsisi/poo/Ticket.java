package es.upm.etsisi.poo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * La clase Ticket representa un ticket de compra en la app. Permite gestionar los productos, los descuentos e
 * imprimir el ticket a traves de sus metodos.
 */
public class Ticket {

    protected Product[] cart;
    protected Double totalPrice;
    private static final int MAX_CART_PRODUCTS = 100;
    protected int productNumber;
    protected Double totalDiscount;
    protected String ticketId;
    protected TicketStatus status;


    /**
     * Constructor de la clase Ticket, que inicializa las variables privadas.
     */
    public Ticket(String ticketId){
        this.cart = new Product[MAX_CART_PRODUCTS];
        this.totalPrice = 0.0;
        this.productNumber = 0;
        this.totalDiscount = 0.0;
        this.ticketId=ticketId;
        this.status=TicketStatus.EMPTY;
    }

    public String getTicketId(){
        return ticketId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Product[] getCart() {
        return cart;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * mira si hay un objeto del tipo evento dentro del ticket igual
     * para evitar que se introduzcan dos iguales
     * @param product producto que se quiere comprovar si ya esta en el ticket
     */
    public boolean reunionIntroduce(Product product){
        boolean found = false ;
        int i = 0 ;
        while (i<productNumber&&!found){
            if(cart[i].getID().equals(product.getID())){
                found=true;
            }
            i++;
        }
        return found;
    }

    /**
     * Añade el producto al array de productos, cart. El producto se inserta en la siguiente posición libre del array.
     * @param producto  Producto a añadir al ticket.
     */
    public void AddProduct(Product producto) throws TiendaUPMExcepcion{
        if(productNumber<MAX_CART_PRODUCTS) {
            cart[productNumber] = producto;
            productNumber++;
            //Arrays.sort(cart,Comparator.comparing(Product::getName));
        }else{ throw new TiendaUPMExcepcion("You are trying to introduce more products to the ticket.", "ERR_MOREPRODUCTS");
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

}
