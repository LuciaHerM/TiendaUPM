package es.upm.etsisi.poo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class TicketComunes extends Ticket{
    public TicketComunes(String ticketId) {
        super(ticketId);
    }

    @Override
    public void AddProduct(Product item) {
        if (item instanceof Services) {
           System.err.println("No puedes introducir un servicio en un ticket comun");
        }
        else {
            super.AddProduct(item);
        }
    }
    @Override
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
                if(cart[i] instanceof  Product_Basic s) {
                    if (s.category != null) {
                        switch (s.getCategory()) {
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
            }
            for (int i = 0; i < productNumber; i++) {
                Double productDiscount = 0.0;

                    if(cart[i] instanceof  Product_Basic s) {
                        switch (s.getCategory()) {
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
