package es.upm.etsisi.poo;

import java.math.BigDecimal;
import java.util.Map;

public class TicketEmpresa extends Ticket {
    private CompanyTicketTipe type;

    public TicketEmpresa(String ticketId, CompanyTicketTipe type) {
        super(ticketId);
        this.type = type;
    }

    @Override
    public void AddProduct(Product item) {
        if (!(item instanceof Services) && (type==CompanyTicketTipe.SERVICES_ONLY)) {
            System.err.println("This bussines ticket can only add services");
        }
        else {
            super.AddProduct(item);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Ticket : ").append(ticketId).append("\n");

        totalPrice = 0.0;
        totalDiscount = 0.0;
        BigDecimal totalPrice1 = BigDecimal.ZERO;
        if(productNumber>0) {

            int serv=0, prod=0;
            for (int i = 0; i < productNumber; i++) {
                if (cart[i] instanceof Services) {
                    serv++;
                }
                else if (cart[i] instanceof Product_Basic || cart[i] instanceof Events) {
                    prod++;
                }
            }

            // --- SERVICES ---
            if(serv>0) {
                sb.append("Services Included: \n");
                for (int i = 0; i < productNumber; i++) {
                    if (cart[i] instanceof Services) {
                        sb.append("  ").append(cart[i].toString()).append("\n");
                    }
                }
            }


            // Si es SOLO servicios, no tiene sentido imprimir precios
            if (type == CompanyTicketTipe.PRODUCTS_AND_SERVICES && prod>0) {
                // --- PRODUCTS ---
                sb.append("Product Included: \n");
                for (int i = 0; i < productNumber; i++) {
                    if (cart[i] instanceof Product_Basic || cart[i] instanceof Events) {
                        Product p = cart[i];
                        sb.append("  ").append(p.toString()).append("\n");
                        BigDecimal price = BigDecimal.valueOf(cart[i].getPrice());
                        BigDecimal priceToAdd;
                        if(cart[i] instanceof Events){
                            priceToAdd = price.multiply(BigDecimal.valueOf(((Events) cart[i]).getInvited_person()));
                        } else {
                            priceToAdd = price;
                        }
                        totalPrice1 = totalPrice1.add(priceToAdd);
                    }
                }
                totalPrice = totalPrice1.doubleValue();
                totalDiscount = totalPrice * (serv * 0.15);
                double finalPrice = totalPrice - totalDiscount;

                sb.append("  Total price: ").append(totalPrice).append("\n");
                sb.append("  Extra Discount from services:")
                        .append(totalDiscount)
                        .append(" **discount -")
                        .append(totalDiscount)
                        .append("\n");

                sb.append("  Total discount: ").append(totalDiscount).append("\n");
                sb.append("  Final Price: ").append(finalPrice);
            }
        }

        return sb.toString();
    }

    public boolean validoCierre(){
        boolean cierre = true;
        if(type==CompanyTicketTipe.PRODUCTS_AND_SERVICES){
            int servicios = 0 ;
            int productos = 0 ;
            for (int i = 0; i < productNumber; i++) {
                if (cart[i] instanceof Services) {
                    servicios++;
                }
                else{
                    productos++;
                }
            }
            cierre=((servicios>0)&&(productos>0));
        }
        return  cierre;
    }
}
