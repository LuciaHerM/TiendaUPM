package es.upm.etsisi.poo;

public enum Category {
    MERCH(0.00),
    STATIONERY(0.05),
    CLOTHES(0.07),
    BOOK(0.10),
    ELECTRONICS(0.03);

    private final double descuento; // Porcentaje de descuento

    Category(double descuento) {
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }
}
