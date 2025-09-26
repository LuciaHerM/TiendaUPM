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
        int numMerch=0;
        int numStationery=0;
        int numClothes=0;
        int numBook=0;
        int numEelctronics=0;
        for(int i = 0 ; i < productNumber ; i++){
            switch (cart[i].Category.toStringh()){
                case 'MERCH':
                    numMerch++;
                    break;
                case 'STATIONERY':
                    numStationery++;
                    break;
                case 'CLOTHES':
                    numClothes++;
                    break;
                case 'BOOK':
                    numBook++;
                    break;
                case 'ELECTRONICS':
                    numEelctronics++;
                    break;
            }
        }
        for(int i = 0 ; i < productNumber ; i++){

        }


     return null;
    }
}
