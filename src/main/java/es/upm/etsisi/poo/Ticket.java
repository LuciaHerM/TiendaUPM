package es.upm.etsisi.poo;

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
        cart[productNumber] = producto;
        productNumber++;
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
     * Crea una representacion textual del ticket con todos los producto, los descuentos aplicados y
     * el precio total. El metodo utiliza un for para contar el numero de productos por categoria y poder aplicar el posible descuento.
     * @return  Cadena con el contenido del ticket.
     */
    public String ToString(){
        int numMerch=0;
        int numStationery=0;
        int numClothes=0;
        int numBook=0;
        int numEelctronics=0;
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
                    numEelctronics++;
                    break;
            }
        }
        for(int i = 0 ; i < productNumber ; i++){
            Double productDiscount=0.0;
            switch (cart[i].getCategory()){
                case MERCH:
                    if(numMerch>=2){
                        productDiscount=0.0;
                    }
                    break;
                case STATIONERY:
                    if(numStationery>=2){
                        productDiscount=0.05;
                    }
                    break;
                case CLOTHES:
                    if(numClothes>=2){
                        productDiscount=0.07;
                    }
                    break;
                case BOOK:
                    if(numBook>=2){
                        productDiscount=0.1;
                    }
                    break;
                case ELECTRONICS:
                    if(numEelctronics>=2){
                        productDiscount=0.03;
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
