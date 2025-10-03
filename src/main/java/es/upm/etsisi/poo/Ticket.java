package es.upm.etsisi.poo;

public class Ticket {
    private Product[] cart;
    private Double totalPrice;
    private static int MAX_CART_PRODUCTS = 100;
    private int productNumber;
    private Double totalDiscount;

    /*
    Constructor de la clase ticket, que asigna valores a las variables privadas.
     */
    public Ticket(){
        this.cart = new Product[MAX_CART_PRODUCTS];
        this.totalPrice = 0.0;
        this.productNumber = 0;
        this.totalDiscount = 0.0;
    }
    /*
    Añade el producto pasado por parámetro en la posición del total de productos
    (debido a que empiezan los arrays en 0)
    */

    public void AddProduct(Product producto) {
        cart[productNumber] = producto;
        productNumber++;
    }
    /*
    Elimina el producto pasado por parámetro.
    Recoloca los productos por si queda un hueco en el array
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
    /*
    Utiliza un bucle para eliminar los productos desde
    el último producto hasta la posición 0.
    */

    public void RemoveAll(){
        for (int i = productNumber - 1; i > 0; i--) {
            cart[i] = null;
        }
    }
    /*
    cuneto con un for el numero de cada tipo de producto
    para luego ir producto a producto aplicando el
    descuento .
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
