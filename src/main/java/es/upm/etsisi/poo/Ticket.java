package es.upm.etsisi.poo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

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
    private String ticketId;
    private TicketStatus status;


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
    public void AddProduct(Product producto) {
        if(productNumber<MAX_CART_PRODUCTS) {
            cart[productNumber] = producto;
            productNumber++;
            //Arrays.sort(cart,Comparator.comparing(Product::getName));
        }else{
            System.out.println("Intentas introducir más productos al Ticket.");
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
    public String toString(){
        totalPrice = 0.0;
        totalDiscount = 0.0;
        BigDecimal totalPrice1 = BigDecimal.ZERO;
        BigDecimal totalDiscount1 = BigDecimal.ZERO;
        StringBuilder str = new StringBuilder();
        str.append("Ticket : " + ticketId + "\n");
        if(!status.equals(TicketStatus.EMPTY)){
            int numMerch = 0;
            int numStationery = 0;
            int numClothes = 0;
            int numBook = 0;
            int numElectronics = 0;
            Arrays.sort(cart, 0, productNumber, (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
            for (int i = 0; i < productNumber; i++) {
                if(cart[i].category!=null) {
                    switch (cart[i].getCategory()) {
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
            }
            for (int i = 0; i < productNumber; i++) {
                Double productDiscount = 0.0;
                if(cart[i].category!=null) {
                    switch (cart[i].getCategory()) {
                        case MERCH:
                            if (numMerch >= 2) {
                                productDiscount = Category.MERCH.getDescuento();
                            }
                            break;
                        case STATIONERY:
                            if (numStationery >= 2) {
                                productDiscount = Category.STATIONERY.getDescuento();
                            }
                            break;
                        case CLOTHES:
                            if (numClothes >= 2) {
                                productDiscount = Category.CLOTHES.getDescuento();
                            }
                            break;
                        case BOOK:
                            if (numBook >= 2) {
                                productDiscount = Category.BOOK.getDescuento();
                            }
                            break;
                        case ELECTRONICS:
                            if (numElectronics >= 2) {
                                productDiscount = Category.ELECTRONICS.getDescuento();
                            }
                            break;
                    }
                }

                BigDecimal price = BigDecimal.valueOf(cart[i].getPrice());
                BigDecimal discount = BigDecimal.valueOf(productDiscount);
                BigDecimal discountFinal = price.multiply(discount).setScale(2, RoundingMode.HALF_UP);

                if (productDiscount == 0.0) {
                    str.append(" " +cart[i].toString() +"\n");
                } else {
                    str.append(" " +cart[i].toString() + " **discount -" + discountFinal +"\n");
                }
                BigDecimal priceToAdd;
                if(cart[i] instanceof Events){
                    priceToAdd = price.multiply(
                            BigDecimal.valueOf(((Events) cart[i]).getInvited_person()));
                } else {
                    priceToAdd = price;
                }
                totalPrice1 = totalPrice1.add(priceToAdd);
                totalDiscount1 = totalDiscount1.add(discountFinal);            }

        }
        totalPrice = totalPrice1.doubleValue();
        totalDiscount = totalDiscount1.doubleValue();
        str.append("  Total price: " + totalPrice + "\n");
        str.append("  Total discount: " + totalDiscount + "\n");
        str.append("  Final Price: " + (totalPrice - totalDiscount));

        return str.toString();
    }
}
