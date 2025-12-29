package es.upm.etsisi.poo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Services extends Product{
    public String id;
    public Category_service category_service;
    public String expiration_day;
    public final double DISCOUNT=0.15;
    public Services(String expiration_day, Category_service category_service, String id) {
        this.category_service=category_service;
        if(check_correct_day()){
           this.expiration_day=expiration_day;
        }else {
            expiration_day=null;
        }
        this.id=id;
    }
    public boolean check_correct_day(){
        LocalDate eventDate;
        try{
            eventDate=LocalDate.parse(expiration_day);
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
}
