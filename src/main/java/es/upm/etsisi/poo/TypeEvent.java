package es.upm.etsisi.poo;

public enum TypeEvent {
    FOOD (3),
    REUNION(0.5);
    // 0.5 DÍAS = 12 horas

    private final double minDaysBefore;

    TypeEvent(double minDaysBefore) {
        this.minDaysBefore = minDaysBefore;
    }
    public double getMinDaysBefore() {
        return minDaysBefore;
    }
}
