package es.upm.etsisi.poo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Events extends Product {
    private static String expiration_day;
    private static final int MAX_PARTICIPANTS=100;
    private static int num_person;
    private static TypeEvent typeEvent;

    public Events(String id, String name, Double price, String expiration_day, int num_person, TypeEvent typeEvent) {
        Events.id=id;
        Events.name=name;
        Events.price=price;
        Events.typeEvent=typeEvent;
        if(check_min_time(expiration_day, typeEvent)){
            Events.expiration_day=expiration_day;
        }
        if(num_person<=MAX_PARTICIPANTS){
            Events.num_person=num_person;
        }else{
            System.err.println("The number of people is surpasses the maximon of people accepted ");
        }
    }

    private static boolean check_min_time(String date, TypeEvent typeEvent){
        LocalDate eventDate;
        try{
            eventDate=LocalDate.parse(date);
        }catch (Exception e){
            System.out.println("The date is not correct written");
            return false;
        }
        LocalDate today=LocalDate.now();
        if(!eventDate.isAfter(today)){
            System.out.println("The date can't be before today");
            return false;
        }
        long daysBetween= ChronoUnit.DAYS.between(today,eventDate);
        if(typeEvent.getMinDaysBefore()>daysBetween){
                System.err.println("The minimum planing time is not of 3 days at least");
                return false;
        }
        return true;
    }

    public static String getExpiration_day() {
        return expiration_day;
    }

    public static void setExpiration_day(String expiration_day) {
        Events.expiration_day = expiration_day;
    }

    public static int getNum_person() {
        return num_person;
    }

    public static void setNum_person(int num_person) {
        Events.num_person = num_person;
    }
}
