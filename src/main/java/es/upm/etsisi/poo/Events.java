package es.upm.etsisi.poo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Events extends Product {
    private String expiration_day;
    private static final int MAX_PARTICIPANTS=100;
    private int num_person;
    private TypeEvent typeEvent;
    private int invited_person=0;

    public Events(String id, String name, Double price, String expiration_day, int num_person, TypeEvent typeEvent) throws TiendaUPMExcepcion{
        super(id, name, price);
        this.typeEvent=typeEvent;
        this.expiration_day=expiration_day;
        this.num_person=num_person;
    }

    public static Events createFromInput(String id, String name, Double price, String expiration_day, int num_person, TypeEvent typeEvent) throws TiendaUPMExcepcion {
        if(num_person>MAX_PARTICIPANTS){
            throw new TiendaUPMExcepcion(
                    "Error: El número de personas (" + num_person + ") excede el máximo permitido (" + MAX_PARTICIPANTS + ").",
                    "ERR_EVENT_CAPACITY");
        }
        else if (check_min_time(typeEvent, expiration_day) && num_person <= MAX_PARTICIPANTS) {

            return new Events(id, name, price, expiration_day, num_person, typeEvent);
        }
        return null;
    }


    public static boolean check_min_time(TypeEvent typeEvent, String expiration_day)throws TiendaUPMExcepcion{
        LocalDate eventDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            eventDate=LocalDate.parse(expiration_day, formatter);
        }catch (Exception e){
            throw new TiendaUPMExcepcion("The date is not correct written must be in this format yyyy-mm-dd", "ERR_DATEFORMAT");
        }
        LocalDate today=LocalDate.now();
        if(!eventDate.isAfter(today)){ throw new TiendaUPMExcepcion("The date can't be before today", "ERR_DATE_PAST");
        }
        double daysBetween= ChronoUnit.HOURS.between(today.atStartOfDay(),eventDate.atStartOfDay())/24.0;

        if(typeEvent.getMinDaysBefore()>daysBetween){
            throw new TiendaUPMExcepcion("The minimum planning time for "+typeEvent+" is "+typeEvent.getMinDaysBefore()+" days.", "ERR_DATE_MIN_DAYS");
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

    public int getMAX_PARTICIPANTS() {
        return MAX_PARTICIPANTS;
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
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
                return " {class:Meeting, id:" + id + ", name:'" + name + "', price:" + price  + ", date of Event:" + expiration_day + ", max people allowed:" + num_person + "}";
            }else {
                return " {class:Food, id:" + id + ", name:'" + name + "', price:" + price + ", date of Event:" + expiration_day + ", max people allowed:" + num_person + "}";
            }
        }
    }
}
