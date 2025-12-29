package es.upm.etsisi.poo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Events extends Product {
    private String expiration_day;
    private final int MAX_PARTICIPANTS=100;
    private int num_person;
    private TypeEvent typeEvent;
    private int invited_person=0;

    public Events(String id, String name, Double price, String expiration_day, int num_person, TypeEvent typeEvent) {
        super(id, name, price);
        this.typeEvent=typeEvent;
        if(check_min_time()){
            this.expiration_day=expiration_day;
        }
        if(num_person<=MAX_PARTICIPANTS){
            this.num_person=num_person;
        }
    }

    public boolean check_min_time(){
        LocalDate eventDate;
        try{
            eventDate=LocalDate.parse(expiration_day);
        }catch (Exception e){
            Notifier.dateIncorrectFormat();
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

    public int getInvited_person() {
        return invited_person;
    }

    public void setInvited_person(int invited_person) {
        this.invited_person = invited_person;
    }

    @Override
    public String toString() {
        if(invited_person!=0) {
            if (typeEvent.equals(TypeEvent.MEETING)){
                return " {class:Meeting, id:" + id + ", name:'" + name + "', price:" + price * invited_person + ", date of Event:" + expiration_day + ", max people allowed:" + num_person + ", actual people in event:" + invited_person + "}";
            }else {
                return " {class:Food, id:" + id + ", name:'" + name + "', price:" + price * invited_person + ", date of Event:" + expiration_day + ", max people allowed:" + num_person + ", actual people in event:" + invited_person + "}";
            }
        }else {
            if (typeEvent.equals(TypeEvent.MEETING)){
                return " {class:Meeting, id:" + id + ", name:'" + name + "', price:" + price * invited_person + ", date of Event:" + expiration_day + ", max people allowed:" + num_person + "}";
            }else {
                return " {class:Food, id:" + id + ", name:'" + name + "', price:" + price * invited_person + ", date of Event:" + expiration_day + ", max people allowed:" + num_person + "}";
            }
        }
    }
}
