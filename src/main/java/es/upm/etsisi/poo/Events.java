package es.upm.etsisi.poo;
import java.time.LocalDate;


public class Events {
    private String expiration_day;
    private static final int MAX_PARTICIPANTS=100;
    private double price_per_person;
    private String name;
    private int num_person;

    public Events(String expiration_day, double price_per_person, String name, int num_person) {
        this.expiration_day=expiration_day;
        this.price_per_person=price_per_person;
        this.name=name;
        if(num_person<=MAX_PARTICIPANTS){
            this.num_person=num_person;
        }else{
            System.err.println("The number of people is surpasses the maximon of people accepted ");
        }
    }
    private void check_min_time(String date){
        int day, year, week;
        String[] array;
        array = date.split("-");
        if(array.length==3){
            day= Integer.parseInt(array[2]);
            year=Integer.parseInt(array[0]);
            week=Integer.parseInt(array[1]);
            if(year==LocalDate.now().getYear() && week==LocalDate.now().getDayOfMonth() && day-LocalDate.now().getDayOfYear()<3){
                System.err.println("The minimum planing time is not of 3 days at least");
            }
        }else{
            System.err.println("The date is not correct written");
        }




    }

    public String getExpiration_day() {
        return expiration_day;
    }

    public double getPrice_per_person() {
        return price_per_person;
    }

    public void setExpiration_day(String expiration_day) {
        this.expiration_day = expiration_day;
    }

    public void setPrice_per_person(double price_per_person) {
        this.price_per_person = price_per_person;
    }
}
