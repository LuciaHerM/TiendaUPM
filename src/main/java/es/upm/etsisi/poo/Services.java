package es.upm.etsisi.poo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class Services extends Product{
    public String id;
    public Category_service category_service;
    public String expiration_day;
    public final double DISCOUNT=0.15;
    public Services(String expiration_day, Category_service category_service, String id) {
        this.category_service=category_service;
        this.expiration_day=expiration_day;
        this.id=id;
    }
    public static Services createFromInput(String expiration_day, Category_service category_service, String id) {
        if (check_correct_day(expiration_day)) {
            return new Services(expiration_day, category_service, id);
        }
        else {
            return null;
        }
    }

    public static boolean check_correct_day(String day){
        LocalDate eventDate;
        try{
            eventDate=LocalDate.parse(day);
        }catch (Exception e){
            Notifier.dateIncorrectFormat();
            return false;
        }
        LocalDate today=LocalDate.now();
        if(!eventDate.isAfter(today)){
            Notifier.dateBeforeToday();
            return false;
        }
        else{
            return true;
        }
    }

    public String getID() {
        return id;
    }

    public Category_service getCategory_service() {
        return category_service;
    }

    public void setCategory_service(Category_service category_service) {
        this.category_service = category_service;
    }

    public String getExpiration_day() {
        return expiration_day;
    }

    public void setExpiration_day(String expiration_day) {
        this.expiration_day = expiration_day;
    }

    public double getDISCOUNT() {
        return DISCOUNT;
    }

    @Override
    public String toString() {
        String expirationFormatted = expiration_day;
        if(expiration_day!=null){
            try {
                LocalDate fecha = LocalDate.parse(expiration_day);
                ZonedDateTime mediaNoche = fecha.atStartOfDay(ZoneId.of("CET"));
                DateTimeFormatter formatear = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy");
                expirationFormatted = mediaNoche.format(formatear);
            } catch(Exception e){
                expirationFormatted = expiration_day;
            }
        }


        return "{class:ProductService, id:" + id + ", category:" + category_service + ", expiration:" + expirationFormatted + "}";
    }
}
