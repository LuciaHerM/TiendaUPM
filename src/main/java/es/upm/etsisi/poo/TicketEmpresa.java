package es.upm.etsisi.poo;

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
            System.err.println("Este ticket de empresa solo admite servicios");
        }
        else {
            super.AddProduct(item);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Ticket : ").append(ticketId).append("\n");

        int serviceCount = 0;
        totalPrice = 0.0;

        // --- SERVICES ---
        sb.append("Services Included: \n");
        for (int i = 0; i < productNumber; i++) {
            if (cart[i] instanceof Services) {
                sb.append("  ").append(cart[i].toString()).append("\n");
                serviceCount++;
            }
        }

        // --- PRODUCTS ---
        sb.append("Product Included\n");
        for (int i = 0; i < productNumber; i++) {
            if (cart[i] instanceof Product) {
                Product p = (Product) cart[i];
                sb.append("  ").append(p.toString()).append("\n");
                totalPrice += p.getPrice();
            }
        }

        // Si es SOLO servicios, no tiene sentido imprimir precios
        if (type == CompanyTicketTipe.PRODUCTS_AND_SERVICES) {
            double discountFromServices = totalPrice * (serviceCount * 0.15);
            double finalPrice = totalPrice - discountFromServices;

            sb.append("  Total price: ").append(totalPrice).append("\n");
            sb.append("  Extra Discount from services:")
                    .append(discountFromServices)
                    .append(" **discount -")
                    .append(discountFromServices)
                    .append("\n");

            sb.append("  Total discount: ").append(discountFromServices).append("\n");
            sb.append("  Final Price: ").append(finalPrice);
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
