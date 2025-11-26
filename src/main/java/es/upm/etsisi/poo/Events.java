package es.upm.etsisi.poo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Events extends Product {
    private String expiration_day;
    private final int MAX_PARTICIPANTS=100;
    private int num_person;
    private TypeEvent typeEvent;

    public Events(String id, String name, Double price, String expiration_day, int num_person, TypeEvent typeEvent) {
        super(id, name, price);
        this.typeEvent=typeEvent;
        if(check_min_time(expiration_day, typeEvent)){
            this.expiration_day=expiration_day;
        }
        if(num_person<=MAX_PARTICIPANTS){
            this.num_person=num_person;
        }else{
            System.out.println("The number of people is surpasses the maximon of people accepted ");
        }
    }

    public static boolean check_min_time(String date, TypeEvent typeEvent){
        LocalDate eventDate;
        try{
            eventDate=LocalDate.parse(date);
        }catch (Exception e){
            System.out.println("The date is not correct written must be in this format yyyy-mm-dd");
            return false;
        }
        LocalDate today=LocalDate.now();
        if(!eventDate.isAfter(today)){
            System.out.println("The date can't be before today");
            return false;
        }
        double daysBetween= ChronoUnit.HOURS.between(today.atStartOfDay(),eventDate.atStartOfDay())/24.0;

        if(typeEvent.getMinDaysBefore()>daysBetween){
                System.out.println("The minimum planning time for "+typeEvent+" is "+typeEvent.getMinDaysBefore()+" days.");
                return false;
        }
        return true;
    }

    public String getExpiration_day() {
        return this.expiration_day;
    }

    public void setExpiration_day(String expiration_day) {
        this.expiration_day = expiration_day;
    }

    public int getNum_person() {
        return this.num_person;
    }

    public void setNum_person(int num_person) {
        this.num_person = num_person;
    }

    @Override
    public String toString() {
        return "{class:Events, id:" + id + ", name:'" + name  + "expiration_day:" + expiration_day + ", num_person:" + num_person + ", typeEvent:" + typeEvent + ", price:" + price + "}";
    }
}
