package es.upm.etsisi.poo;

public enum Category {
    MERCH(0),
    STATIONERY(5),
    CLOTHES(7),
    BOOK(10),
    ELECTRONICS(3);

    private final int descuento; // Porcentaje de descuento

    Category(int descuento) {
        this.descuento = descuento;
    }

    public int getDescuento() {
        return descuento;
    }
}
